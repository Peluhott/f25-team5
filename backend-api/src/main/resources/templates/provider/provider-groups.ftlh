<#ftl encoding="UTF-8">

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Your Groups</title>

    <link rel="stylesheet" href="/providers/css/style.css">

    <link rel="stylesheet" href="https://site-assets.fontawesome.com/releases/v7.0.0/css/all.css"/>
    <link rel="stylesheet" href="https://site-assets.fontawesome.com/releases/v7.0.0/css/sharp-solid.css"/>
    <link rel="stylesheet" href="https://site-assets.fontawesome.com/releases/v7.0.0/css/sharp-regular.css"/>
    <link rel="stylesheet" href="https://site-assets.fontawesome.com/releases/v7.0.0/css/sharp-light.css"/>
    <link rel="stylesheet" href="https://site-assets.fontawesome.com/releases/v7.0.0/css/duotone.css"/>
</head>

<body>
    <aside>
        <ul>
            <a href="/provider/home/${providerId}" class="sidebtn" id="home-page"><li><i class="fa-solid fa-house"></i></li></a>
            <a href="#" class="sidebtn" id="notification-link"><li><i class="fa-solid fa-bell"></i></li></a>
            <a href="/provider/profile/${providerId}" class="sidebtn" id="people-link"><li><i class="fa-solid fa-user"></i></li></a>

            <div id="break"></div>

            <a href="/provider/${providerId}/reviews" class="sidebtn" id="reviews-link"><li><i class="fa-solid fa-envelope"></i></li></a>
            <a href="/provider/${providerId}/applications" class="sidebtn" id="group-link"><li><i class="fa-solid fa-people-group"></i></li></a>

            <a href="/Prototype/Provider/Login.html" class="sidebtn" id="logout">
                <li><i class="fa-solid fa-door-open"></i></li>
            </a>
        </ul>
    </aside>

    <header>
        <img src="/customer/images/logo-transparent.png" id="logo">
        <h1>GroupLink</h1>
    </header>

    <div class="main-content">

        <#-- CASE: No groups -->
        <#if groups?size == 0>
            <div class="group-create">
                <p>Create a Group</p>
                <a href="/group/create">
                    <img src="/images/createbutton.png" class="create-icon">
                </a>
            </div>

        <#-- CASE: Render group cards -->
        <#else>
            <div class="group-container">

                <#list groups as group>
                    <div class="group-card">

    <!-- Group Image at the top -->
    <img src="/${group.profilePicturePath}" alt="Group Picture" class="group-image">

    <!-- Group Title -->
    <h4 class="group-title">${group.name}</h4>

    <!-- Group Description -->
    <#if group.content?? && group.content?has_content>
        <p class="group-description">${group.content}</p>
    </#if>

    <!-- Group Location -->
    <#if group.location?? && group.location?has_content>
        <p class="group-location">Location: ${group.location}</p>
    </#if>

    <!-- Member Count -->
    <p class="group-members">Members: ${group.activeMem}</p>

    <!-- View Details Button -->
    <a href="/provider/${provider.id}/group/${group.id}">
        <button class="reply-btn">View Details</button>
    </a>

</div>

                </#list>
                <div class="group-create">
                <p>Create a Group</p>
                <a href="/group/create/${providerId}">
                    <img src="/groups/createbutton.png" class="create-icon">
                </a>
            </div>

            </div>
        </#if>

    </div>

</body>
</html>
