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
            <a href="/provider/group/1" title="Home" class="sidebtn" id="home-page">
                <li><i class="fa-solid fa-house"></i></li>
            </a>
            
            <a href="#" title="Notifications" class="sidebtn" id="notification-link">
                <li><i class="fa-solid fa-bell"></i></li>
            </a>
            
            <a href="/provider/1/applications" title="Profile" class="sidebtn" id="people-link">
                <li><i class="fa-solid fa-user"></i></li>
            </a>

            <div id="break"></div>

            <a href="#" title="Messages" class="sidebtn" id="reviews-link">
                <li><i class="fa-solid fa-envelope"></i></li>
            </a>

            <a href="#" title="Your Groups" class="sidebtn" id="group-link">
                <li><i class="fa-solid fa-people-group"></i></li>
            </a>

            <a href="/Prototype/Provider/Login.html" id="logout" title="Logout" class="sidebtn">
                <li><i class="fa-solid fa-door-open"></i></li>
            </a>
        </ul>
    </aside>

    <header>
        <img src="<#if logoPath??>${logoPath}<#else>../Customer/images/logo-transparent.png</#if>" alt="" id="logo">
        <h1>${appName! "GroupLink"}</h1>
    </header>

    <div class="main-content">

        
        <div class="group-create">
        <form action="/group/create" method="post" enctype="multipart/form-data" class="create-group-form">
            <label>Group Name:</label>
            <input type="text" name="name" required>

            <label>Location:</label>
            <input type="text" name="location" required>

            <label>Type:</label>
            <select name="type" required>
                <option value="">Select type...</option>
                <option value="Hackathon">Hackathon</option>
                <option value="Startup">Startup</option>
                <option value="School">School</option>
                <option value="Charity">Charity</option>
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
