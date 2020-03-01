<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <title>Регистрация</title>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link
            rel="stylesheet"
            href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
            integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
            crossorigin="anonymous"
    >

    <style type="text/css">
        .signup-row {
            display: inline-flex;
            flex-direction: row;
            width: 100%;
        }

        .signup-row > span {
            flex: 1;
        }
        .signup-row > input {
            flex: 2;
        }
    </style>
</head>
<body>
    <div class="jumbotron text-center">
        <h1>Регистрация</h1>
        <p>Создав новый аккаунт, вы сможете арендовать автомобили в любое время</p>
        <a href="/"><button class="btn btn-link">Вернуться назад</button></a>
    </div>

    <div class="container">
        <div class="row">
            <div class="col-sm-3"></div>
            <div class="col-sm-6">
                <form method="POST" action="/signup">
                    <div class="signup-row">
                        <span>Email:</span>
                        <input type="text" name="email" required placeholder="hello@world.com" />
                    </div>
                    <div class="signup-row">
                        <span>Пароль:</span>
                        <input type="password" name="password" required placeholder="*****" />
                    </div>
                    <div class="signup-row">
                        <span>Полное имя:</span>
                        <input type="text" name="name" required placeholder="Василий Анатольевич Петров" />
                    </div>
                    <div class="signup-row">
                        <span>Дата рождения:</span>
                        <input type="date" name="birthDate" required placeholder="01.01.1991" />
                    </div>
                    <div class="signup-row">
                        <span>Номер телефона:</span>
                        <input type="text" name="phone" required placeholder="+7 (777) 777 77 77" />
                    </div>
                    <div class="signup-row">
                        <span>Адрес:</span>
                        <input type="text" name="address" required placeholder="Москва, пр. Ленина 4">
                    </div>
                    <div style="text-align: center; margin-top: 20px;">
                        <input class="btn btn-secondary" type="submit" value="Продолжить" />
                    </div>
                </form>
            </div>
            <div class="col-sm-3"></div>
        </div>
    </div>

</body>
</html>
