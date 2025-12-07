<#ftl encoding="UTF-8">

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${pageTitle! "Provider"}</title>

    <link rel="stylesheet" href="/providers/css/style.css">
    <script src="https://kit.fontawesome.com/b69a734b4d.js" crossorigin="anonymous"></script>
    <script src="<#if baseUrl??>${baseUrl}</#if>/script.js" defer></script>

    <link rel="stylesheet" href="https://site-assets.fontawesome.com/releases/v7.0.0/css/all.css"/>
    <link rel="stylesheet" href="https://site-assets.fontawesome.com/releases/v7.0.0/css/sharp-solid.css"/>
    <link rel="stylesheet" href="https://site-assets.fontawesome.com/releases/v7.0.0/css/sharp-regular.css"/>
    <link rel="stylesheet" href="https://site-assets.fontawesome.com/releases/v7.0.0/css/sharp-light.css"/>
    <link rel="stylesheet" href="https://site-assets.fontawesome.com/releases/v7.0.0/css/duotone.css"/>
</head>
<body>
    <aside>
        <ul>
            <a href="/provider/home/${providerId}" title="Home" class="sidebtn" id="home-page">
                <li><i class="fa-solid fa-house"></i></li>
            </a>
            
            <a href="#" title="Notifications" class="sidebtn" id="notification-link">
                <li><i class="fa-solid fa-bell"></i></li>
            </a>
            
            <a href="/provider/profile/${providerId}" title="Profile" class="sidebtn" id="people-link">
                <li><i class="fa-solid fa-user"></i></li>
            </a>

            <div id="break"></div>

            <a href="/provider/${providerId}/reviews" title="Messages" class="sidebtn" id="reviews-link">
                <li><i class="fa-solid fa-envelope"></i></li>
            </a>

            <a href="/provider/${providerId}/applications" title="Your Groups" class="sidebtn" id="group-link">
                <li><i class="fa-solid fa-people-group"></i></li>
            </a>

            <a href="/Prototype/Provider/Login.html" id="logout" title="Logout" class="sidebtn">
                <li><i class="fa-solid fa-door-open"></i></li>
            </a>
        </ul>
    </aside>

    <header>
        <img src="/customer/images/logo-transparent.png" id="logo">
        <h1>GroupLink</h1>
    </header>

    <div class="main-content">

    <#if (people?size == 0)>
        <div>
            <p>No current applications</p>
        </div>
    <#else>
        <div class="people-container">
            <#list people as person>
                <div class="person-card">

                    <img
                        class="customer-picture"
                        src="/customer/images/${person.customer.profilePicturePath!'nullpfp.jpg'}"
                        alt="${person.customer.username! 'Applicant'} Picture"
                    />

                    <div class="person-details">

                        <h3 class="customer-name">
                            ${person.customer.username}
                        </h3>

                        <p class="group-request">
                            Requesting to join:
                            <strong>${person.group.name}</strong>
                        </p>

                        <p class="customer-message">
                            ${person.message}
                        </p>

                        <form action="/application/updateStatus" method="post" class="membership-actions">
                            <input type="hidden" name="membershipId" value="${person.id}" />
                            <input type="hidden" name="providerId" value="${person.group.provider.id}"/>

                            <div class="person-btns">
                                <button type="submit" name="status" value="active" class="btn-accept">
                                    Accept
                                </button>

                                <button type="submit" name="status" value="inactive" class="btn-deny">
                                    Deny
                                </button>
                            </div>
                        </form>

                    </div>

                </div>
            </#list>
        </div>
    </#if>

    </div>
</body>
</html>