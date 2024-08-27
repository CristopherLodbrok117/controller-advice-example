Las anotaciones @ControllerAdvice y @RestControllerAdvice permiten utilizar las mismas técnicas de manejo de excepciones con un alcance global en nuestra aplicación.

En Spring, el primer enfoque consistia en manejar las excepciones dentro de la capa del controlador. Cada clase anotada con @Controller, podia manejar internamente sus excepciones, incluyendo métodos anotados con @ExceptionHandler(YourException.class) e implementando el tratamiento de la excepción tras capturarla.

Dicho enfoque no era óptimo, ya que distintos controladores de nuestra aplicación solian arrojar los mismos tipos de excepciones, y para solucionarlo se tenian que escribir los mismos métodos una y otra vez en cada controlador. Un desarrollador pensó que sería fantástico si pudieran crear un punto centralizado para las excepciones, de modo que cada vez que cualquier controlador lance una excepción, sea capturada y manejada por ese punto en lugar de tener repetir cada exception handler en cada clase de controlador. @ControllerAdvice entró en juego.

Aunque su alcance es global, tambien podemos acotar definir a que controladores nuestro ControllerAdvice estara manejando sus excepciones, definiendo sus packages:
@ControllerAdvice("org.example.controllers")

@RestControllerAdvice vs @ControllerAdvice

@ControllerAdvice se introdujo en Spring 3.2 y es un @Component especializado que nos permite declarar métodos @ExceptionHandler, @InitBinder o @ModelAttribute para compartirlos entre las clases @Controller.

Dicho de otra manera, podemos tratarlo como un interceptor controlado por anotaciones, cuyos métodos se aplicarán a toda la aplicación (no sólo a un controlador individual).

Por otro lado, @RestControllerAdvice combina las anotaciones @ControllerAdvice y @ResponseBody.
La ventaja de incluir @ResponseBody  es que el valor de retorno de un método sera directamente inyectadoal response body, sin necesidar de utilizar ResponseEntity<T> (o HttpEntity<T>)

Aqui podemos visualizar su  definición: https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/bind/annotation/RestControllerAdvice.html 
