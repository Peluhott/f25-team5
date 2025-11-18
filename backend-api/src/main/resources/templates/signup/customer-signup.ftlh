<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="/Prototype/Customer/images/logo-transparent.png">
    <title>GroupLink</title>
    <link rel="stylesheet" href="/signup/css/customer-signup.css">
</head>
<body>

    <header>
        <img src="/signup/images/logo-transparent.png" alt="" id="logo">
        <h1>GroupLink</h1>
    </header>

    <div id="create">
        <h3>Sign Up</h3>
        <#if error??>
            <p class="error" style="color: red;">${error}</p>
        </#if>
        <form action="/customer/signup" method="post" enctype="multipart/form-data">

            <input type="text" name="username" id="username" placeholder="Username" required>

            <input type="text" name="email" name="email" placeholder="Email" required>

            <input type="password" name="password" id="password" placeholder="Password" required>

            <input type="password" name="re-password" id="re-password" placeholder="Re-enter Password" required>

            <input type="file" name="profilePicturePath" id="profilePicturePath" placeholder="Profile Picture">

                <input type="submit" id="signup" value="Sign Up">
        </form>

        <p>Already a user?
            <a href="/customer/login">Login</a>
        </p>
    </div>
</body>
</html>