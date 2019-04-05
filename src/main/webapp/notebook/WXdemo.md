
## Chapter 6 多媒体

目前支持 image , camera , audio , video 等

### 图片

引入一个图片

    <image style="{{style}}" mode="{{mode}}" src="{{src}}"></image>
    
循环展示一个数组中的图片数据

    <view class="section section_gap" wx:for="{{array}}" wx:for-item="item">
        <view>{{item.text}}</view>
        <view>
        <image style="width:200px; height:200px; background-color: #eeeeee;"
         mode="{{item.mode}}" src="{{item.src}}">
        </view>
    </view>
    
属性
    
1. src - 地址
2. mode - 裁剪、缩放模式，默认 scaleToFill
3. lazy-load - 懒加载，默认 false，只针对 page 与 scroll-view 下的 image 有效
4. binderror - 错误发生时，发布到 AppService 的事件名
5. bindload - 图片加载完毕，发布到 AppService 的事件名


### 相机

用法

  需要用户授权 scope.camera
  
  示例代码
  
    