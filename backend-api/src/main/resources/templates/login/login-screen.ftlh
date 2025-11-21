<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="/login/images/logo-transparent.png">
    <title>GroupLink</title>
    <link rel="stylesheet" href="/login/css/login.css">
</head>
<body>
    <header>
        <img src="/login/images/logo-transparent.png" alt="" id="logo">
        <h1>GroupLink</h1>
    </header>

    <div class="login">
        <h2>Login</h3>
            <#if error??>
                <p class="error" style="color: red;">${error}</p>
            </#if>
        <form action="/customer/login" method="post">
            <label for="username">Username:</label>
            <input type="text" name="username" id="username" placeholder="Enter your username">

            <label for="password">Password:</label>
            <input type="password" name="password" id="password" placeholder="Enter your password">

            <input type="submit" value="Login">
        </form>

        <p>Not Registered? 
            <a href="/customer/signup-options">Create an Account</a>
        </p>

    </div>

</body>
</html>