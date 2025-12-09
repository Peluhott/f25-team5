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

            <a href="/customer/reviews/${customerId}" title="Your Reviews" class="sidebtn">
                <li><i class="fa-solid fa-hand-holding-star"></i></li>
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
        <img style="object-fit: cover;" src="/provider/images/${(group.provider.profilePicturePath)!'nullpfp.jpg'}"
            alt="" class="provider-pfp">
        <div id="provider-details">
            <div class="provider-name">${group.provider.username}</div>
            <div class="rating">
                <div id="stars">
                    <#if group.provider.averageRating < 1>
                     <i class="fa-solid fa-star-half"></i>
                        <#elseif group.provider.averageRating < 2>
                            <i class="fa-sharp fa-solid fa-star"></i><i class="fa-solid fa-star-half"></i>
                            <#elseif group.provider.averageRating < 3>
                                <i class="fa-sharp fa-solid fa-star"></i><i class="fa-sharp fa-solid fa-star"></i><i class="fa-solid fa-star-half"></i>
                                <#elseif group.provider.averageRating < 4>
                                    <i class="fa-sharp fa-solid fa-star"></i><i class="fa-sharp fa-solid fa-star"></i><i
                                        class="fa-sharp fa-solid fa-star"></i><i class="fa-solid fa-star-half"></i>
                                    <#elseif group.provider.averageRating < 5>
                                        <i class="fa-sharp fa-solid fa-star"></i><i class="fa-sharp fa-solid fa-star"></i><i
                                            class="fa-sharp fa-solid fa-star"></i><i class="fa-sharp fa-solid fa-star"></i><i class="fa-solid fa-star-half"></i>
                                            <#else>
                                                <i class="fa-sharp fa-solid fa-star"></i><i class="fa-sharp fa-solid fa-star"></i><i
                                                    class="fa-sharp fa-solid fa-star"></i><i class="fa-sharp fa-solid fa-star"></i><i
                                                    class="fa-sharp fa-solid fa-star"></i>
                    </#if>
            </div>
            <div class="rating-number">${group.provider.averageRating}</div>
            <div class="rating-reviews">(${group.provider.reviewCount} reviews)</div>
        </div>
    </div>
    <div id="banner">
        <img style="height: 100%; width: 100%; object-fit: cover;"
            src="/${(group.profilePicturePath)!'sampleGroupPoster.webp'}" alt="" id="banner-img">
    </div>

    <div id="info">
        <div class="location"><i class="fa-regular fa-earth-americas"></i>Location: ${group.location}</div>
        <div class="member-count"><i class="fa-solid fa-people-group"></i>Member Count:
            ${group.activeMem}/${group.maxMem}</div>

        <div id="actions">
            <#if success??>
                <p class="success" style="color: green; margin-bottom: -1%; margin-top: 1%;">${success}</p>
            </#if>
            <#if (isInactive?? && !isInactive) || (isRemoved?? && !isRemoved)>
                <form action="/customer/review/${id}/${customerId}" method="post" id="review-form">
                    <label for="rating" style="font-weight: bold;">Host Rating:</label>
                    <select name="rating" id="" style="width: 20%; background-color: #F2BFA4;">
                        <option value="5">5 Stars</option>
                        <option value="4">4 Stars</option>
                        <option value="3">3 Stars</option>
                        <option value="2">2 Stars</option>
                        <option value="1">1 Star</option>
                    </select>
                    <label for="message" style="font-weight: bold;">Write a message for your review.</label>
                    <textarea name="message" rows="5" cols="20" placeholder="The host was amazing to work with..."
                        required style="background-color: #F2BFA4;"></textarea>
                    <button id="review" type="submit" style="font-weight: bold;"><i
                            class="fa-solid fa-star-sharp-half-stroke"></i>Leave Review</button>
                </form>

                <#elseif isMember?? && isMember>
                    <form action="/customer/group/leave/${id}/${customerId}" method="post">
                        <button id="leave" class="actionBTN" style="background-color: rgb(234, 113, 113);
    margin-top: 10%; font-weight: bold;"><i class="fa-sharp fa-solid fa-arrow-left-from-bracket"></i>Leave
                            Group</button>
                    </form>

                    <#elseif isPending?? && isPending>
                        <form action="/customer/group/cancelApplication/${id}/${customerId}" method="get">
                            <button id="cancel" class="actionBTN" style="background-color: rgb(234, 113, 113);
    margin-top: 10%; font-weight: bold;"><i class="fa-solid fa-ban"></i>Cancel Application</button>
                        </form>
            </#if>
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
                        <img src="/customer/images/${(review.customer.profilePicturePath)!'nullpfp.jpg'}" alt=""
                            class="reviewer-pfp">
                        <div class="review-content">
                            <div class="reviewer-name">${review.customer.username}</div>
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
                        </div>
                    </div>
                    <div class="review-text">
                        ${review.message}
                    </div>
                </div>
                <#else>
                    <p id="no-results" style="font-size: xx-large; font-weight: bold; margin-left: 5%; margin-top: 1%;">
                        No reviews
                        found</p>
            </#list>
        </div>

    </div>
    </div>

</body>

</html>