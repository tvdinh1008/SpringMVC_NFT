# SpringMVC_NFT


+ IoC Container trong Spring chính là lõi của Spring Framework. IoC Container sẽ tạo ra các đối tượng, nối chúng lại với nhau, cấu hình chúng, và quản lý vòng đời của chúng từ khi tạo ra đến khi bị hủy. IoC Container sử dụng DI (Dependency Injection) để quản lý các thành phần tạo nên một ứng dụng. Những đối tượng này được gọi là Spring Bean
+ Có hai loại IoC Container, đó là:
    - BeanFactory
    - ApplicationContext
    
 + Các annotation trong spring:
  
 + Nếu sử dụng spring-integration mqtt thì chú ý để version của maven trùng với spring mvc và version 4.x.x sẽ có chút khác với 5.x.x nhé

+ Spring security + Jwt cho api . Khi truy cập vào api ->ta sẽ thêm 1 lớp filter cho api qua 1 bước kiểm tra 
 http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
 và khi triển khai ta sẽ kiểm tra mã jwt có trong request ko. Nếu có-> ktra nó
