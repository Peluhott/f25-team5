<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="/Prototype/Customer/images/logo-transparent.png">
    <title>GroupLink</title>
    <link rel="stylesheet" href="/customer/css/customer-groups.css">

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

            <a href="/Prototype/Customer/messages.html" title="Messages">
                <li><i class="fa-solid fa-envelope"></i></li>
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

        <div id="active-groups">
            <h2>Active Groups:</h2>
            <#list activeGroupMembershipsList as groupMembership>
            <div class="card">
                <img class="poster" src="/groups/images/${(group.profilePicturePath)!'sampleGroupPoster.webp'}" alt="">
                <div class="user">
                    <img class="poster-pfp" src="/provider/images/${(group.provider.profilePicturePath)!'nullpfp.jpg'}" alt="">
                    <h5>${groupMembership.group.provider.username}</h5>
                    <#switch groupMembership.group.type>
                        <#on "Hackathon">
                        <div class="card-type"><i class="fa-solid fa-code"></i>${groupMembership.group.type}</div>
                        <#on "Study">
                        <div class="card-type"><i class="fa-solid fa-book"></i>${groupMembership.group.type}</div>
                        <#on "Volunteer">
                        <div class="card-type"><i class="fa-solid fa-handshake-angle"></i>${groupMembership.group.type}</div>
                        <#on "Gaming">
                        <div class="card-type"><i class="fa-jelly fa-regular fa-gamepad"></i>${groupMembership.group.type}</div>
                        <#on "Sports">
                        <div class="card-type"><i class="fa-sharp fa-solid fa-court-sport"></i> ${groupMembership.group.type}</div>
                        <#default>
                        <div class="card-type"><i class="fa-solid fa-people-line"></i>${groupMembership.group.type}</div>
                    </#switch>
                </div>
                <p class="card-description">${groupMembership.group.name}</p>
                <p class="card-description">View Group</p>
            </div>
            <#else>
            <p id="no-results" style="font-size: xx-large; font-weight: bold; margin-left: 40%; margin-top: 1%;">No active groups found</p>
            </#list>
        </div>

        <div id="inactive-groups">
            <h2>Inactive Groups:</h2>

            <#list inactiveGroupMembershipsList as groupMembership>
            <div class="card">
                <img class="poster" src="/groups/images/${(group.profilePicturePath)!'sampleGroupPoster.webp'}" alt="">
                <div class="user">
                    <img class="poster-pfp" src="/provider/images/${(group.provider.profilePicturePath)!'nullpfp.jpg'}" alt="">
                    <h5>${groupMembership.group.provider.username}</h5>
                    <#switch groupMembership.group.type>
                        <#on "Hackathon">
                        <div class="card-type"><i class="fa-solid fa-code"></i>${groupMembership.group.type}</div>
                        <#on "Study">
                        <div class="card-type"><i class="fa-solid fa-book"></i>${groupMembership.group.type}</div>
                        <#on "Volunteer">
                        <div class="card-type"><i class="fa-solid fa-handshake-angle"></i>${groupMembership.group.type}</div>
                        <#on "Gaming">
                        <div class="card-type"><i class="fa-jelly fa-regular fa-gamepad"></i>${groupMembership.group.type}</div>
                        <#on "Sports">
                        <div class="card-type"><i class="fa-sharp fa-solid fa-court-sport"></i> ${groupMembership.group.type}</div>
                        <#default>
                        <div class="card-type"><i class="fa-solid fa-people-line"></i>${groupMembership.group.type}</div>
                    </#switch>
                </div>
                <p class="card-description">${groupMembership.group.name}</p>
                <p class="card-description">View Group</p>
            </div>
            <#else>
            <p id="no-results" style="font-size: xx-large; font-weight: bold; margin-left: 40%; margin-top: 1%;">No inactive groups found</p>
            </#list>
        </div>

        <div id="pending-groups">
            <h2>Pending Group Memberships:</h2>

            <#list pendingGroupMembershipsList as groupMembership>
            <div class="card">
                <img class="poster" src="/groups/images/${(group.profilePicturePath)!'sampleGroupPoster.webp'}" alt="">
                <div class="user">
                    <img class="poster-pfp" src="/provider/images/${(group.provider.profilePicturePath)!'nullpfp.jpg'}" alt="">
                    <h5>${groupMembership.group.provider.username}</h5>
                    <#switch groupMembership.group.type>
                        <#on "Hackathon">
                        <div class="card-type"><i class="fa-solid fa-code"></i>${groupMembership.group.type}</div>
                        <#on "Study">
                        <div class="card-type"><i class="fa-solid fa-book"></i>${groupMembership.group.type}</div>
                        <#on "Volunteer">
                        <div class="card-type"><i class="fa-solid fa-handshake-angle"></i>${groupMembership.group.type}</div>
                        <#on "Gaming">
                        <div class="card-type"><i class="fa-jelly fa-regular fa-gamepad"></i>${groupMembership.group.type}</div>
                        <#on "Sports">
                        <div class="card-type"><i class="fa-sharp fa-solid fa-court-sport"></i> ${groupMembership.group.type}</div>
                        <#default>
                        <div class="card-type"><i class="fa-solid fa-people-line"></i>${groupMembership.group.type}</div>
                    </#switch>
                </div>
                <p class="card-description">${groupMembership.group.name}</p>
                <p class="card-description">View Group</p>
            </div>
            <#else>
            <p id="no-results" style="font-size: xx-large; font-weight: bold; margin-left: 40%; margin-top: 1%;">No pending memberships found</p>
            </#list>
        </div>
    </div>

</body>

</html>