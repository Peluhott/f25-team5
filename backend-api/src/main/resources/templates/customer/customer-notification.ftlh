<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="/Prototype/Customer/images/logo-transparent.png">
    <title>GroupLink</title>
    <link rel="stylesheet" href="/customer/css/customer-notification.css">

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link
        href="https://fonts.googleapis.com/css2?family=Saira+Semi+Condensed:wght@100;200;300;400;500;600;700;800;900&display=swap"
        rel="stylesheet">
    <script src="https://kit.fontawesome.com/b69a734b4d.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://site-assets.fontawesome.com/releases/v7.0.0/css/all.css" />

    <link rel="stylesheet" href="https://site-assets.fontawesome.com/releases/v7.0.0/css/sharp-solid.css" />

    <link rel="stylesheet" href="https://site-assets.fontawesome.com/releases/v7.0.0/css/sharp-regular.css" />

    <link rel="stylesheet" href="https://site-assets.fontawesome.com/releases/v7.0.0/css/sharp-light.css" />

    <link rel="stylesheet" href="https://site-assets.fontawesome.com/releases/v7.0.0/css/duotone.css" />
</head>

<body>

    <header>
        <img src="/customer/images/logo-transparent.png" alt="" id="logo">
        <h1>${title}</h1>
    </header>

    <aside>
        <ul>
            <a href="/customer/profile/${id}" title="Profile">
                <li><i class="fa-solid fa-user"></i></li>
            </a>

            <div id="break"></div>

            <a href="/customer/home/${id}" title="Home">
                <li> <i class="fa-solid fa-house"></i></li>
            </a>

            <a href="/customer/notifications/${id}" title="Notifications">
                <li> <i class="fa-solid fa-bell"></i></li>
            </a>

            <a href="/Prototype/Customer/messages.html" title="Your Reviews" class="sidebtn">
                <li><i class="fa-solid fa-hand-holding-star"></i></li>
            </a>

            <a href="/customer/groups/${id}" title="Your Groups">
                <li><i class="fa-solid fa-people-group"></i></li>
            </a>

            <a href="/customer/login" id="logout" title="Logout">
                <li id="logout"><i class="fa-solid fa-door-open"></i></li>
            </a>
        </ul>
    </aside>


    <div id="main-content">

        <#list notificationsList as notification>

            <div class="notification">
                <#switch notification.type>
                    <#on "accepted">
                        <div class="icon"><i class="fa-solid fa-thumbs-up"></i></div>
                        <#on "rejected">
                            <div class="icon"><i class="fa-solid fa-thumbs-down"></i></div>
                            <#on "explore">
                                <div class="icon"><i class="fa-solid fa-rocket-launch"></i></div>
                                <#on "hazard">
                                    <div class="icon"><i class="fa-solid fa-circle-exclamation"></i></div>
                                    <#default>
                                        <div class="icon"><i class="fa-solid fa-bell"></i></div>
                </#switch>
                <div class="content">${notification.message}</div>
                <div class="date">${notification.timestamp}</div>
            </div>
                <#else>
                    <p id="no-results"
                        style="font-size: xx-large; font-weight: bold; margin-left: 30%; margin-top: 10%;">No new
                        notifications. <i class="fa-regular fa-face-smile"></i></p>
        </#list>
    </div>

</body>

</html>