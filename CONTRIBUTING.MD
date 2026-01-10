# For Developers

HUD Manager的整个实现都仅对界面渲染的“准备过程”作修改，不触及渲染底层，保证其轻量性和强大的兼容性。

## 1. GuiGraphics

各界面元素通过`render`方法启动渲染，通过参数`GuiGraphics`控制大小、位置等，并绘制形状、文字和物品等纹理。

`GuiGraphics.pose`字段存储矩阵栈`Matrix3x2fStack`，通过其`scale`和`translate`方法可对其缩放与移动。

缩放以屏幕左上角为中心，通过放缩`Window.guiScaledWidth`和`Window.guiScaledHeight`，可以保持位置不变。

对于`Tooltip`，还需要调整相关方法的`mouseX`、`mouseY`，以及显式构造的`ClientTooltipPositioner`参数。

`GuiGraphics`的大部分方法都可以传入参数`color`，其默认值为`0xFFFFFFFF`，通过改变这一参数，可以改变颜色。

## 2. GuiRenderState

`GuiGraphics`最终将数据提交给`GuiRenderState`，其通过`pose`和`color`等字段接收并存储后续渲染所需数据。

渲染物品的`GuiItemRenderState`没有`color`字段，由字段`STORED_OPACITY`和接口`UnaryIntOperator`补充。

这也为渲染过大物品的`OversizedItemRenderState`提供了颜色信息，因为其包含一个`GuiItemRenderState`对象。

对于`PictureInPictureRenderState`，其原版实现没有`pose`和`color`字段，且第三方Mod能够注册任意的实现。

因此使用`HudManager.STORAGE`将任意`PictureInPictureRenderState`与活跃的`HudManager`关联以补充信息。

## 3. GuiRenderer

`GuiRenderState`将由`GuiRenderer`处理，其中上述数据将被渲染为实际的纹理，再次提交给`GuiRenderState`。

`GuiRenderer#submitBlitFromItemAtlas`方法最终将`GuiItemRenderState`转换为`BlitRenderState`。

`PictureInPictureRenderer#blitTexture`方法最终将`PictureInPictureRenderState`转换为`BlitRenderState`。

`OversizedItemRenderState`也是`PictureInPictureRenderState`的实现，但其处理方法同`GuiItemRenderState`。

通过改变上述方法所得`BlitRenderState`的值，可将物品、饼图等也给予正确的大小、位置和颜色，实现完整功能。
