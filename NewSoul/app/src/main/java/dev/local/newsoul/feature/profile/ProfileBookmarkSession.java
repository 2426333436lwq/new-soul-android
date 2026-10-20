package dev.local.newsoul.feature.profile;

/**
 * 单次资料页会话状态（原 {@code t1}）。
 *
 * <p>以宿主 Fragment 实例为 key 登记在 {@link ProfileBookmarkActions#SESSIONS} 中，
 * 承载菜单项注入时快照下来的账号与目标用户信息，以及列表加载 / 切换插眼的进行状态。</p>
 *
 * <p>字段语义（逐字对应原 {@code t1}）：</p>
 * <ul>
 *   <li>{@code account}（{@code a}）：菜单注入时的当前登录账号快照；</li>
 *   <li>{@code userId}（{@code b}）：目标用户主页标识；</li>
 *   <li>{@code displayName}（{@code c}）：目标用户昵称（{@code alias} 非空时）否则签名；</li>
 *   <li>{@code menuItem}（{@code d}）：注入的菜单项对象；</li>
 *   <li>{@code loaded}（{@code e}）：插眼列表是否已加载成功；</li>
 *   <li>{@code bookmarked}（{@code f}）：当前是否已插眼；</li>
 *   <li>{@code inProgress}（{@code g}）：是否有切换插眼操作进行中；</li>
 *   <li>{@code loadFailed}（{@code h}）：插眼列表加载是否失败。</li>
 * </ul>
 */
final class ProfileBookmarkSession {

    final String account;
    final String userId;
    final String displayName;
    final Object menuItem;

    boolean loaded;
    boolean bookmarked;
    boolean inProgress;
    boolean loadFailed;

    ProfileBookmarkSession(String account, String userId, String displayName, Object menuItem) {
        this.account = account;
        this.userId = userId;
        this.displayName = displayName;
        this.menuItem = menuItem;
    }
}
