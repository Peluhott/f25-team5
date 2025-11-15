<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="/Prototype/Customer/images/logo-transparent.png">
    <title>GroupLink</title>
    <link rel="stylesheet" href="/customer/css/customer-home.css">

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Saira+Semi+Condensed:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">
    <script src="https://kit.fontawesome.com/b69a734b4d.js" crossorigin="anonymous"></script>
    <link
    rel="stylesheet"
    href="https://site-assets.fontawesome.com/releases/v7.0.0/css/all.css"
  />

  <link
    rel="stylesheet"
    href="https://site-assets.fontawesome.com/releases/v7.0.0/css/sharp-solid.css"
  />

  <link
    rel="stylesheet"
    href="https://site-assets.fontawesome.com/releases/v7.0.0/css/sharp-regular.css"
  />

  <link
    rel="stylesheet"
    href="https://site-assets.fontawesome.com/releases/v7.0.0/css/sharp-light.css"
  />

  <link
    rel="stylesheet"
    href="https://site-assets.fontawesome.com/releases/v7.0.0/css/duotone.css"
  />
</head>
<body>
    <header>
        <img src="/customer/images/logo-transparent.png" alt="" id="logo">
        <h1>${title}</h1>
    </header>
    
    <aside>
        <ul>
            <a href="/Prototype/Customer/profile.html" title="Profile" class="sidebtn">
                <li><i class="fa-solid fa-user"></i></li>
            </a>

            <div id="break"></div>

            <a href="/Prototype/Customer/index.html" title="Home" class="sidebtn">
                <li> <i class="fa-solid fa-house"></i></li>
            </a>

            <a href="/Prototype/Customer/notifications.html" title="Notifications" class="sidebtn">
                <li> <i class="fa-solid fa-bell"></i></li>
            </a>

            <a href="/Prototype/Customer/messages.html" title="Messages" class="sidebtn">
                <li><i class="fa-solid fa-envelope"></i></li>
            </a>

            <a href="/Prototype/Customer/groups.html" title="Your Groups" class="sidebtn">
                <li><i class="fa-solid fa-people-group"></i></li>
            </a>

            <a href="/Prototype/Login/Login.html" id="logout" title="Logout" class="sidebtn">
                <li id="logout"><i class="fa-solid fa-door-open"></i></li>
            </a>
        </ul>
    </aside>

    <div id="filters">
        <form action="" id="search">
            <input type="search" name="" id="" placeholder="What are you looking for?">
            <input type="submit" value="Search">
        </form>

        <div id="options">
           <div id="group-type" class="filter">Group options <i class="fa-solid fa-caret-down"></i></div>
           <div id="leader-rating" class="filter">Minimum Rating <i class="fa-solid fa-caret-down"></i></div>
           <div id="max-members" class="filter">Member Count <i class="fa-solid fa-caret-down"></i></div>
           <div id="location" class="filter">Location<i class="fa-solid fa-caret-down"></i></div>
        </div>

        <div id="options-selected">
            <div class="selected">Any Groups <i class="fa-solid fa-x"></i></div>
            <div class="selected">Any Rating <i class="fa-solid fa-x"></i></div>
            <div class="selected">Any Member # <i class="fa-solid fa-x"></i></div>
        </div>

        <form action="" id="sortby">
            <label for="">Sort by:</label>
            <select name="" id="">
                <option value="">Newest</option>
                <option value="">Oldest</option>
            </select>
        </form>
    </div>


    <div id="main-content">

        <#list groupList as group>
        <a href="/Prototype/Customer/apply.html" class="link-card">
            <div class="card">
                <img class="poster" src="${(group.profilePicturePath)!'/customer/images/sampleGroupPoster.webp'}" alt="">
                <div class="user">
                    <img class="poster-pfp" src="${(group.profilePicturePath)!'/customer/images/nullpfp.jpg'}" alt="">
                    <h5>${group.provider.username}</h5>
                    <#switch group.type>
                        <#on "Hackathon">
                        <div class="card-type"><i class="fa-solid fa-code"></i>${group.type}</div>
                        <#on "Study">
                        <div class="card-type"><i class="fa-solid fa-book"></i>${group.type}</div>
                        <#on "Volunteer">
                        <div class="card-type"><i class="fa-solid fa-handshake-angle"></i>${group.type}</div>
                        <#default>
                        <div class="card-type"><i class="fa-solid fa-people-line"></i>${group.type}</div>
                    </#switch>
                </div>
                <p class="card-group-name">${group.name}</p>
                <div class="rating">
                    <i class="fa-solid fa-star"></i>
                    <div class="rating-number">${group.provider.averageRating}</div>
                    <div class="rating-reviews">(${group.provider.reviewCount})</div>
                </div>
                <div class="member-count">
                    <i class="fa-solid fa-people-group"></i>
                    <div class="count-number">${group.activeMem}/${group.maxMem}</div>
                </div>
                <div class="location"><i class="fa-regular fa-earth-americas"></i> ${group.location}</div>
            </div>
        </a>
        </#list>
        
    </div>
</body>
</html>