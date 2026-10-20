package dev.local.newsoul.feature.chat;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import dev.local.newsoul.framework.HostClasses;
import dev.local.newsoul.framework.ValueCodec;

/**
 * 消息体复刻（原模块 {@code F.c} / {@code F.d} / {@code F.e}）。
 *
 * <p>复读与转发都需要把「宿主消息对象」重新构造成一个可独立发送的消息体：
 * 宿主消息携带了整个会话上下文，直接复用会串到错误的会话，因此必须按类型逐个字段拷贝。</p>
 *
 * <p>支持的消息类型与校验规则（与宿主一致）：</p>
 * <ul>
 *   <li><b>文本</b>：{@code TextMsg.text} 去空白后非空且长度不超过 500，否则 {@code "Invalid text"}。</li>
 *   <li><b>单图</b>：{@code ImgMsg} 的 {@code imageUrl} 必须是直链、宽高为正，否则 {@code "Image not uploaded"}。</li>
 *   <li><b>多图</b>：{@code ImgMsgs.imgMsgList} 非空且逐项满足单图校验，否则 {@code "Empty images"}。</li>
 *   <li><b>语音</b>：{@code AudioMsg.url} 必须是直链且 {@code duration > 0}，否则 {@code "Invalid audio"}。</li>
 * </ul>
 *
 * <p>类型或承载类不匹配时抛 {@code "Unsupported message body"}；内容为空抛 {@code "Missing body"}。
 * 异常携带的文案与原模块逐字一致。</p>
 */
public final class MessageRewrite {

    /** 文本消息最大长度。 */
    private static final int MAX_TEXT_LENGTH = 500;

    private static final int TYPE_TEXT = 1;
    private static final int TYPE_IMAGE = 2;
    private static final int TYPE_IMAGES = 3;
    private static final int TYPE_AUDIO = 5;

    /** 单图消息需要逐项拷贝的字段，顺序与原实现一致。 */
    private static final String[] IMAGE_FIELDS = {"imageUrl", "imageW", "imageH"};

    private MessageRewrite() {
    }

    /** 由宿主 {@code ChatMessage} 重建可独立发送的消息体（原 {@code F.d}）。 */
    public static Object fromChatMessage(Object chatMessage) throws ReflectiveOperationException {
        int type = ((Number) HostAccess.call(chatMessage, "getMsgType")).intValue();
        Object content = HostAccess.call(chatMessage, "getMsgContent");
        return rebuild(type, content);
    }

    /** 按消息类型重建消息体（原 {@code F.c}）。 */
    public static Object rebuild(int type, Object content) throws ReflectiveOperationException {
        if (content == null) {
            throw new IllegalArgumentException("Missing body");
        }
        String className = content.getClass().getName();
        if (type == TYPE_TEXT && HostClasses.IMLIB_TEXT_MSG.equals(className)) {
            return rebuildText(content);
        }
        if (type == TYPE_IMAGE && HostClasses.IMLIB_IMG_MSG.equals(className)) {
            return copyImage(content);
        }
        if (type == TYPE_IMAGES && HostClasses.IMLIB_IMG_MSGS.equals(className)) {
            return rebuildImages(content);
        }
        if (type == TYPE_AUDIO && HostClasses.IMLIB_AUDIO_MSG.equals(className)) {
            return rebuildAudio(content);
        }
        throw new IllegalArgumentException("Unsupported message body");
    }

    private static Object rebuildText(Object content) throws ReflectiveOperationException {
        String text = (String) HostAccess.readField(content, "text");
        if (text == null || text.trim().isEmpty() || text.length() > MAX_TEXT_LENGTH) {
            throw new IllegalArgumentException("Invalid text");
        }
        Constructor<?> constructor = content.getClass().getConstructor(String.class);
        return constructor.newInstance(text);
    }

    private static Object rebuildImages(Object content) throws ReflectiveOperationException {
        List<?> images = (List<?>) HostAccess.readField(content, "imgMsgList");
        if (images == null || images.isEmpty()) {
            throw new IllegalArgumentException("Empty images");
        }
        List<Object> copies = new ArrayList<>();
        for (Object image : images) {
            copies.add(copyImage(image));
        }
        Constructor<?> constructor = content.getClass().getConstructor(List.class);
        return constructor.newInstance(copies);
    }

    private static Object rebuildAudio(Object content) throws ReflectiveOperationException {
        String url = (String) HostAccess.readField(content, "url");
        int duration = ((Integer) HostAccess.readField(content, "duration")).intValue();
        if (!ValueCodec.isDirectHttpUrl(url) || duration <= 0) {
            throw new IllegalArgumentException("Invalid audio");
        }
        Constructor<?> constructor = content.getClass()
                .getConstructor(String.class, String.class, int.class, String.class);
        return constructor.newInstance(url, "", duration, "");
    }

    /** 拷贝单条图片消息（原 {@code F.e}）。 */
    private static Object copyImage(Object image) throws ReflectiveOperationException {
        if (image == null || !HostClasses.IMLIB_IMG_MSG.equals(image.getClass().getName())) {
            throw new IllegalArgumentException("Invalid image");
        }
        String url = (String) HostAccess.readField(image, "imageUrl");
        if (ValueCodec.isDirectHttpUrl(url)) {
            int width = ((Integer) HostAccess.readField(image, "imageW")).intValue();
            if (width > 0) {
                int height = ((Integer) HostAccess.readField(image, "imageH")).intValue();
                if (height > 0) {
                    Object copy = image.getClass().getConstructor().newInstance();
                    for (String name : IMAGE_FIELDS) {
                        image.getClass().getField(name).set(copy, HostAccess.readField(image, name));
                    }
                    return copy;
                }
            }
        }
        throw new IllegalArgumentException("Image not uploaded");
    }
}