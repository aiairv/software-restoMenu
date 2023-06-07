# software-restoMenu

**Электронное меню ресторана**
Онлайн-платформа для заказа еды в ресторане

**Авторы**
Бакытбекова Гулкайыр
Кубатова Айдай

**Цель проекта**
 Cозданиe удобной онлайн-платформы для клиентов ресторана, которые хотят заказать еду. 
 Приложение предоставляет доступ к меню ресторана, позволяет пользователям выбирать блюда и осуществлять заказ онлайн без необходимости звонить и делать заказ по телефону.
  
**Особенности проекта**
Пользователи могут просматривать меню ресторана и выбирать блюда.
Приложение позволяет осуществлять заказ и оплату еды онлайн.
Ресторан может добавлять и редактировать данные о блюдах и категориях.
Реализовано REST API для получения информации о блюдах и категориях из базы данных.
  
**Технологии**
Язык: JAVA (jdk 8 и выше) + Spring Boot
СУБД: PostgreSQL
Сборщик проектов: Maven

**Установка**
Установка PostgreSQL, создание БД и пользователей.
    Обязательные переменные:
    - DATABASES_NAME - emenu
    - DATABASES_USER - postgres
    - DATABASES_PASSWORD - postgres

**Примеры использования**
Получение информации о категории:
GET localhost:9090/api/category/
Получение информации об ингрединте
GET localhost:9090/api/ingredients/1
Получение информации о блюде:
GET localhost:9090/api/dishes/1
Добавление блюда в корзину
POST localhost:9090/api/cart/addDish?userId=1&dishId=1
Создание заказа:
POST localhost:9090/api/order/create?cartId=1&comment=быстрее&place=Table1

**Ссылка для просмотра колекции POSTMAN для просмотра роутов**
https://api.postman.com/collections/25576067-dbae968f-9778-487d-893e-1143e094a985?access_key=PMAT-01H1C25X87MJ7WE0KD713D6RS6


=
