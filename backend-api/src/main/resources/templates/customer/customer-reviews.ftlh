<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="/Prototype/Customer/images/logo-transparent.png">
    <title>GroupLink</title>
    <link rel="stylesheet" href="/customer/css/customer-reviews.css">

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
            <a href="/customer/profile/${id}" title="Profile" class="sidebtn">
                <li><i class="fa-solid fa-user"></i></li>
            </a>

            <div id="break"></div>

            <a href="/customer/home/${id}" title="Home" class="sidebtn">
                <li> <i class="fa-solid fa-house"></i></li>
            </a>

            <a href="/customer/notifications/${id}" title="Notifications" class="sidebtn">
                <li> <i class="fa-solid fa-bell"></i></li>
            </a>

            <a href="/customer/reviews/${id}" title="Your Reviews" class="sidebtn">
                <li><i class="fa-solid fa-hand-holding-star"></i></li>
            </a>

            <a href="/customer/groups/${id}" title="Your Groups" class="sidebtn">
                <li><i class="fa-solid fa-people-group"></i></li>
            </a>

            <a href="/customer/login" id="logout" title="Logout" class="sidebtn">
                <li id="logout"><i class="fa-solid fa-door-open"></i></li>
            </a>
        </ul>
    </aside>

    <div id="main-content">

        <#list reviewsList as review>
            <div class="card">
                <div class="user">
                    <img class="poster-pfp" src="/provider/images/${(review.provider.profilePicturePath)!'nullpfp.jpg'}" alt="">
                    <h3>${review.provider.username}</h3>
                    <div class="rating-number"> <i class="fa-solid fa-star"></i>${review.provider.averageRating}</div>
                    <div class="rating-reviews">Total Reviews: (${review.provider.reviewCount}) </div>
                </div>
                
                <div class="card-break"></div>
                <div class="review-message">
                    <p style="font-weight: bold;">Your Review:</p>
                    <#switch review.rating>
                                <#on 1>
                                    <div class="review-rating"><i class="fa-sharp fa-solid fa-star"></i>
                                        ${review.rating}</div>
                                    <#on 2>
                                        <div class="review-rating"><i class="fa-sharp fa-solid fa-star"></i><i
                                                class="fa-sharp fa-solid fa-star"></i> ${review.rating}</div>
                                        <#on 3>
                                            <div class="review-rating"><i class="fa-sharp fa-solid fa-star"></i><i
                                                    class="fa-sharp fa-solid fa-star"></i><i
                                                    class="fa-sharp fa-solid fa-star"></i> ${review.rating}</div>
                                            <#on 4>
                                                <div class="review-rating"><i class="fa-sharp fa-solid fa-star"></i><i
                                                        class="fa-sharp fa-solid fa-star"></i><i
                                                        class="fa-sharp fa-solid fa-star"></i><i
                                                        class="fa-sharp fa-solid fa-star"></i></i> ${review.rating}
                                                </div>
                                                <#on 5>
                                                    <div class="review-rating"><i
                                                            class="fa-sharp fa-solid fa-star"></i><i
                                                            class="fa-sharp fa-solid fa-star"></i><i
                                                            class="fa-sharp fa-solid fa-star"></i><i
                                                            class="fa-sharp fa-solid fa-star"></i><i
                                                            class="fa-sharp fa-solid fa-star"></i> ${review.rating}
                                                    </div>
                            </#switch>
                    <p>${review.message}</p>
                    <p style="font-weight: bold;">Host Response:</p>
                    <#if review.response??>
                        <p style="margin-top: -2%;">${review.response}</p>
                    <#else>
                        <p style="margin-top: -2%;"> No response from host yet.</p>
                    </#if>
                </div>
                <form action="/customer/review/delete/${review.id}/ ${review.customer.id}" method="post">
                    <button type="submit" style="background-color: rgb(255, 147, 147); font-weight: bold; margin: 5px; cursor: pointer;">Delete Review</button>
                </form>
            </div>

        <#else>
            <p id="no-results" style="font-size: xx-large; font-weight: bold; margin-left: 40%; margin-top: 15%;">No reviews found.<i class="fa-light fa-face-disappointed"></i></p>
        </#list>
        
    </div>
</body>
</html>