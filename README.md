Project for practice.

* Enterprise JavaBeans (EJB):
1. Java Message Service (JMS)
2. Stateless, Singleton, MDB 
3. Timer (BackendJob)
4. Transaction Management
* Contexts and Dependency Injection (CDI):
1. Interceptor
2. Producer
3. CDI beans : Request, Session, Application 
* Java Persistence API (JPA):
1. Object-Relational Mapping (ORM)
2. QueryDSL
3. NamedQuery
4. Inheritance - SINGLE_TABLE, JOINED
* Web Services:
1. Java API for RESTful Web Services
2. Java API for XML Web Services
* Gradle
* JBoss (wildfly-10.1.0.Final)
* Slf4j + Log4j

Приложение - Блог:
------------------
- Создать категорию
- Изменить категорию
- Удалить категорию
- Блог может иметь иметь несколько категорий или не иметь вообще
- Пользователь может оставлять комментарии
- У комментариев есть иерархия(многоуровневая)
- Нотификация автору блога о комментарии
- Письмо автору блога о комментарии

Тех задание:
------------
- Сервер WildFly
- База MySQL
- 2 типа комментариев - блог и категория - применить наследование SINGLE_TABLE
- ID блога и категории должны быть уникальными - применить наследование JOINED
- BackendJob, которая чистит нотификации старше 2 недель 
- Отправка письма должна быть асинхронной (JMS)
- JaxWS web service, который отдает в виде XML блоги по заданным датам/категориям
- Использовать QueryDSL
- JaxRS, который создает, редактирует, удаляет, читает блоги
- Use gradle
- Use logging SLF4J with log4j
- CDI - сделать аннотацию аналог @Value в Spring
- CDI - logger interceptor, если у метода есть входные параметры и если есть результат