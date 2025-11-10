# Implementation

HUD Manager的整个实现都仅对界面渲染的“准备过程”作修改，不触及渲染底层，保证其轻量性和强大的兼容性。

由于Minecraft反编译后的代码没有任何注释，可读性很差，该文档将大致描述实现过程，以供本人和其他人后续开发所用。

## GuiGraphics

各界面元素通过`render`方法启动渲染，通过参数`GuiGraphics`控制大小、位置与图层，并绘制形状、文字和物品等纹理。

`GuiGraphics.pose`字段存储矩阵栈`Matrix3x2fStack`，通过其`scale`和`translate`方法可以实现界面元素的缩放和移动。

其中缩放是以屏幕左上角为中心的，通过放缩`Window.guiScaledWidth`和`Window.guiScaledHeight`，就可以保持位置的正确。

`GuiGraphics`的大部分方法都可以传入一个32位整数`color`，其默认值为`0xFFFFFFFF`，通过改变这一参数，就可以改变颜色。

## GuiRenderState

`GuiGraphics`最终将数据提交给`GuiRenderState`，其通过`pose`和`color`等字段接收并存储后续渲染所需数据，但有例外。

渲染物品的`GuiItemRenderState`没有`color`字段，其信息由`GuiItemRenderStateMixin.STORED_COLOR`补充。

这也为渲染过大物品的`OversizedItemRenderState`提供了颜色信息，因为其包含一个`GuiItemRenderState`对象。

渲染饼图的`GuiProfilerChartRenderState`没有`pose`和`color`字段，其信息由`Manager.DEBUG_SCREEN`补充。

`OversizedItemRenderState`和`GuiProfilerChartRenderState`均为`PictureInPictureRenderState`的实现。

## GuiRenderer

`GuiRenderState`将由`GuiRenderer`处理，其中上述数据将被渲染为实际的纹理，再次提交给`GuiRenderState`。

`GuiRenderer#submitBlitFromItemAtlas`方法最终将`GuiItemRenderState`转换为`BlitRenderState`。

`PictureInPictureRenderer#blitTexture`方法最终将`PictureInPictureRenderState`转换为`BlitRenderState`。

通过改变上述方法所得`BlitRenderState`的值，就可将物品和饼图也给予正确的大小、位置和颜色，实现完整功能。
