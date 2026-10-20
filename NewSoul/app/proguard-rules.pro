# New Soul 模块混淆规则
# 模块入口与全部 hook 实现类通过反射 / libxposed 元数据加载，必须保持名称
-keep class dev.local.newsoul.** { *; }
-keep class io.github.libxposed.** { *; }

# 保留 Xposed 元数据与资源
-keepattributes *Annotation*, Signature, InnerClasses, EnclosingMethod

# 反射访问的成员
-keepclassmembers class * {
    <fields>;
    <methods>;
}
-dontwarn io.github.libxposed.**
-dontwarn androidx.annotation.**