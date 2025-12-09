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
    <script src="/providers/js/script.js" defer></script>

</head>
<body>
    <aside>
        <ul>
            <a href="/provider/home/${providerId}" title="Home" class="sidebtn" id="home-page">
                <li><i class="fa-solid fa-house"></i></li>
            </a>
            
            <a href="/provider/${providerId}/notifications" title="Notifications" class="sidebtn" id="notification-link">
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

            <a href="/provider/login" id="logout" title="Logout" class="sidebtn">
                <li><i class="fa-solid fa-door-open"></i></li>
            </a>
        </ul>
    </aside>

     <header>
    <img src="/customer/images/logo-transparent.png" id="logo" alt="GroupLink logo">
    <h1>GroupLink</h1>
  </header>

    <div class="main-content">

        
        <div class="group-create">
        <form action="/group/create" method="post" enctype="multipart/form-data" class="create-group-form">
            <input type="hidden" name="providerId" value="${providerId!}">
            <label>Group Name:</label>
            <input type="text" name="name" required>

            <label>Location:</label>
<div class="autocomplete-wrapper">
    <input type="text" id="location-input" name="location" autocomplete="off" required>
    <div id="location-suggestions" class="autocomplete-list" style="display: none;"></div>
</div>


            <label>Type:</label>
            <select name="type" required>
                <option value="">Select type...</option>
                <option value="Hackathon">Hackathon</option>
                <option value="Study">Study</option>
                <option value="Sports">Sports</option>
                <option value="Volunteer">Volunteer</option>
                <option value="Gaming">Gaming</option>
            </select>

            <label>Max Members:</label>
            <input type="number" name="maxMem" min="1" required>

            <label>Content / Description:</label>
            <textarea name="content" rows="5" required></textarea>

            <label>Profile Picture:</label>
            <input type="file" name="picture" accept="image/*">

            <button type="submit">Create Group</button>
        </form>
    </div>

    </div>
</body>
</html>
