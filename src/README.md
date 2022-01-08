##项目设计预期以及简要事先方案预计  
程序可以包含若干特定的暂停态,若干临时态,可以实现多开,但是共享同一个"数据库"  
对于暂停态和临时态又可以在放大态和缩小态之间切换  
可选:文件拖动获得可存储地址,主要在于拖动信息的获取  
程序的开放和文件夹或链接的存储可以分类也可以混合展示,多种状态可选  
同时也应提供一些log恢复技术支持  
<br>
type:轻量级程序,部分可以轻实现,比如多占用内存,文件读写而不是数据库  
过程性逻辑  
打开程序,即可选用某个模型,作为暂时态  
也可新建暂时态,暂时态可以被保存,以供今后使用,+tip通常文件的打开地址被认为是不重要的
于是对据此建立的设置项可以设为不保留记录.  
其他,比如软件的打开,网址可以保留记录.  
主界面除了包含主要的app,网页,还有文件外,还包含设置和切换寻找暂态的选项框  
文件可以在启动时读取所有的图片或者读取需要的图片,这个最好可以在设置里面进行设置.  
实际上暂态主要用于网页和文件链接存储,不仅应添加开放逻辑,还应添加拷贝/粘贴的逻辑  

实现:  
分别设计独立的app打开类,网页处理类(打开/文本处理/视图),文件处理类(这个和网页处理类很像,
可以设计为两者从同一个类继承或者同一个接口进行不同实现)  
初始阶段的实现可以先设置为主界面处理一个大对象,持有app,网页,文件地址的 数据信息.这三种处理同一个对象
或者对于不同的类型分发不同的数据(差不多).按钮时切换一个动态的jpanel,实际上jpanel对象信息也可以被携带,
设置可以在初始化时进行初始好,就是可能会更加占用内存.