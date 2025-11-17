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

            <a href="/customer/home" title="Home" class="sidebtn">
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

            <a href="/customer/login" id="logout" title="Logout" class="sidebtn">
                <li id="logout"><i class="fa-solid fa-door-open"></i></li>
            </a>
        </ul>
    </aside>

    <div id="filters">
        <form action="/customer/home/search" method="get" id="search">
            <input type="search" name="query" id="query" placeholder="What are you looking for?">

            <input type="hidden" name="type" value="${type?if_exists}">
            <input type="hidden" name="location" value="${location?if_exists}">
            <input type="hidden" name="rating" value="${rating?if_exists}">
            <button type="submit" id="search-btn">Search</button>
        </form>

        <h2>Filters:</h2>
        <div id="options">
            <form action="/customer/home/filter" method="get">
                <label for="type">Group Types: </label>
                <select name="type" id="type" class="filter">
                    <#assign selectedValue = RequestParameters.type!"">
                    <option value="" <#if "" == selectedValue>selected="selected"</#if>> Any Group Type </option>
                    <#list groupTypes as type>
                        <option value="${type}" <#if type == selectedValue>selected="selected"</#if>> ${type} </option>
                    </#list>
                </select>
                <label for="location">Locations: </label>
                <select name="location" id="location" class="filter">
                    <#assign selectedValue = RequestParameters.location!"" >
                    <option value="" <#if "" == selectedValue>selected="selected"</#if>> Any Location </option>
                    <#list groupLocations as location>
                        <option value="${location}" <#if location == selectedValue>selected="selected"</#if>> ${location} </option>
                    </#list>
                </select> 

                <label for="rating">Host Rating: </label>
                <select name="rating" id="rating" class="filter">
                    <#assign selectedValue = RequestParameters.rating!"0.0" >
                    <option value="0.0" <#if "0.0" == selectedValue>selected="selected"</#if>> Any Rating </option>
                    <option value="5.0" <#if "5.0" == selectedValue>selected="selected"</#if>> 5 Stars </option>
                    <option value="4.0" <#if "4.0" == selectedValue>selected="selected"</#if>> 4 Stars & Up </option>
                    <option value="3.0" <#if "3.0" == selectedValue>selected="selected"</#if>> 3 Stars & Up </option>
                    <option value="2.0" <#if "2.0" == selectedValue>selected="selected"</#if>> 2 Stars & Up </option>
                    <option value="1.0" <#if "1.0" == selectedValue>selected="selected"</#if>> 1 Star & Up </option>
                </select>

                <input type="hidden" name="query" value="${query?if_exists}">
                
                <button type="submit" id="filter-btn">Filter</button>
            </form>
        </div>
    </div>


    <div id="main-content">

        <#list groupList as group>
        <a href="/group/details/${group.id?c}" class="link-card">
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

        <#else>
            <p id="no-results" style="font-size: xx-large; font-weight: bold; margin-left: 30%; margin-top: 10%;">No groups found matching your criteria. <i class="fa-light fa-face-disappointed"></i></p>
        </#list>
        
    </div>
</body>
</html>