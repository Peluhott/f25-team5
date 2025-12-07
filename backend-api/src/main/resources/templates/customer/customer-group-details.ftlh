<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="/Prototype/Customer/images/logo-transparent.png">
    <title>GroupLink</title>
    <link rel="stylesheet" href="/customer/css/customer-group-details.css">

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
            <a href="/customer/profile/${customerId}" title="Profile">
                <li><i class="fa-solid fa-user"></i></li>
            </a>

            <div id="break"></div>

            <a href="/customer/home/${customerId}" title="Home">
                <li> <i class="fa-solid fa-house"></i></li>
            </a>

            <a href="/customer/notifications/${customerId}" title="Notifications">
                <li> <i class="fa-solid fa-bell"></i></li>
            </a>

            <a href="/Prototype/Customer/messages.html" title="Messages">
                <li><i class="fa-solid fa-envelope"></i></li>
            </a>

            <a href="/customer/groups/${customerId}" title="Your Groups">
                <li><i class="fa-solid fa-people-group"></i></li>
            </a>

            <a href="/customer/login" id="logout" title="Logout">
                <li id="logout"><i class="fa-solid fa-door-open"></i></li>
            </a>
        </ul>
    </aside>
    <div id="main-content">
        <h2>${group.name}</h2>
        <img style="object-fit: cover;" src="/provider/images/${(group.provider.profilePicturePath)!'nullpfp.jpg'}" alt="" class="provider-pfp">
        <div id="provider-details">
            <div class="provider-name">${group.provider.username}</div>
            <div class="rating">
                <div id="stars">
                    <i class="fa-sharp fa-solid fa-star"></i><i class="fa-sharp fa-solid fa-star"></i><i class="fa-sharp fa-solid fa-star"></i><i class="fa-sharp fa-solid fa-star"></i><i class="fa-sharp fa-solid fa-star"></i>
                </div>
                <div class="rating-number">${group.provider.averageRating}</div>
                <div class="rating-reviews">(${group.provider.reviewCount} reviews)</div>
            </div>
        </div>
        <div id="banner">
            <img style="height: 100%; width: 100%; object-fit: cover;" src="/groups/images/${(group.profilePicturePath)!'sampleGroupPoster.webp'}" alt="" id="banner-img">
        </div>

        <div id="info" style="height: 35%;">
            <div class="location"><i class="fa-regular fa-earth-americas"></i>Location: ${group.location}</div>
            <div class="member-count"><i class="fa-solid fa-people-group"></i>Member Count: ${group.activeMem}/${group.maxMem}</div>

            <div id="actions">
                <button id="message"><i class="fa-solid fa-message"></i>Message host</button>
                <button id="review"><i class="fa-solid fa-star-sharp-half-stroke"></i>Leave Review</button>
            </div>

        </div>
        <div id="description">
            <h3>About this Group:</h3>
            ${group.content}
        </div>

        <div id="host-reviews">
            <h3>Host Reviews:</h3>
            <div class="review-cardstack">
                <#list reviewList as review>
                <div class="review-card">
                    <div class="reviewer-content">
                            <img src="/customer/images/${(review.customer.profilePicturePath)!'nullpfp.jpg'}" alt="" class="reviewer-pfp">
                        <div class="review-content">
                            <div class="reviewer-name">${review.customer.username}</div>
                            <#switch review.rating>
                            <#on 1>
                            <div class="review-rating"><i class="fa-sharp fa-solid fa-star"></i> ${review.rating}</div>
                            <#on 2>
                            <div class="review-rating"><i class="fa-sharp fa-solid fa-star"></i><i class="fa-sharp fa-solid fa-star"></i> ${review.rating}</div>
                            <#on 3>
                            <div class="review-rating"><i class="fa-sharp fa-solid fa-star"></i><i class="fa-sharp fa-solid fa-star"></i><i class="fa-sharp fa-solid fa-star"></i> ${review.rating}</div>
                            <#on 4>
                            <div class="review-rating"><i class="fa-sharp fa-solid fa-star"></i><i class="fa-sharp fa-solid fa-star"></i><i class="fa-sharp fa-solid fa-star"></i><i class="fa-sharp fa-solid fa-star"></i></i> ${review.rating}</div>
                            <#on 5>
                            <div class="review-rating"><i class="fa-sharp fa-solid fa-star"></i><i class="fa-sharp fa-solid fa-star"></i><i class="fa-sharp fa-solid fa-star"></i><i class="fa-sharp fa-solid fa-star"></i><i class="fa-sharp fa-solid fa-star"></i> ${review.rating}</div>
                            </#switch>
                        </div>
                    </div>
                    <div class="review-text">
                        ${review.message}
                    </div>
                </div>
                <#else>
            <p id="no-results" style="font-size: xx-large; font-weight: bold; margin-left: 5%; margin-top: 1%;">No reviews found</p>
                </#list>
            </div>
            
        </div>
    </div>
    
</body>
</html>