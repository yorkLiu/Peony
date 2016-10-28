Netflix使用了细粒度的SOA架构，这是我们基于云的部署模式的根基。目前，我们运行着上百个细粒度服务，使用诸如Netflix API Services这样的“边缘服务（Edge Service）”，共同负责处理面向客户的请求。轻量级的、基于REST的协议，是在这些服务之间进行内部通信的必然之选。

Netflix Internal Web Service Framework（简称NIWS）构成了该架构的基础。我们之前发布的Eureka，起到服务发现的作用。与Eureka一起，NIWS提供执行REST调用需要的所有组件。

NIWS由REST客户端和服务器端框架构成，基于Java的JSR-311RESTful API规范。我们的服务使用多种负载数据序列化格式，比如Avro、XML、JSON、Thrift和Google Protocol Buffers。NIWS提供序列化和反序列化机制。


For more details: http://www.infoq.com/cn/news/2013/01/netflix-annouced-ribbon/

-- 