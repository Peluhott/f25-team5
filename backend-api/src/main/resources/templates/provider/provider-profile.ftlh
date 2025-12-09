<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="/Prototype/Customer/images/logo-transparent.png">
    <title>GroupLink - Provider Profile</title>
    
  <link rel="stylesheet" href="/providers/css/provider-profile.css">

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
        <h1>Profile</h1>
    </header>

    <aside>
        <ul>

            
            <a href="/provider/profile/${id}" title="Profile">
                <li><i class="fa-solid fa-user"></i></li>
            </a>

            <div id="break"></div>

            <a href="/provider/home/${id}" title="Home">
                <li> <i class="fa-solid fa-house"></i></li>
            </a>

            <a href="/provider/${id}/notifications" title="Notifications">
                <li> <i class="fa-solid fa-bell"></i></li>
            </a>

            <a href="/provider/${id}/reviews" title="Messages">
                <li><i class="fa-solid fa-envelope"></i></li>
            </a>

            <a href="/provider/${id}/applications" title="Your Groups">
                <li><i class="fa-solid fa-people-group"></i></li>
            </a>

            <a href="/provider/login" id="logout" title="Logout">
                <li id="logout"><i class="fa-solid fa-door-open"></i></li>
            </a>


        </ul>
    </aside>

    <div id="main-content">

        <div id="change-pfp">
            <form action="/provider/profile/${id}/updateProfilePicture" method="post" id="change-pfp-form" enctype="multipart/form-data">
                <h2>Change Profile Picture:</h2>
                <#if errorPFP??>
                <p class="errorPFP" style="color: red;">${errorPFP}</p>
                </#if>
                <#if successPFP??>
                <p class="successPFP" style="color: green;">${successPFP}</p>
                </#if>
                <img src="/provider/images/${provider.profilePicturePath}" alt="" id="current-pfp">
                <input type="file" name="profilePicturePath" id="cpi">
                <input type="submit" value="Save Changes" id="change-pfp-submit" class="cps">
            </form>
            <!-- Delete provider account -->
        <form action="/provider/profile/${id}/delete" method="post"
              onsubmit="return confirmProviderDelete();">
            <input type="hidden" name="providerId" value="${id}">
            <button type="submit" class="btn-danger">
                Delete Account
            </button>
        </form>
        </div>

        <form action="/provider/profile/${id}/updateUsername" method="post" id="personal-information-form">
            <h2>Change Username: ${provider.username}</h2>
            <#if error??>
            <p class="error" style="color: red;">${error}</p>
            </#if>
            <#if success??>
            <p class="success" style="color: green;">${success}</p>
            </#if>
            <label for="username" class="pil">New Username</label>
            <input type="text" name="username" id="username" placeholder="Enter New Username" class="pit">

            <label for="email" class="pil">Email</label>
            <input type="text" name="email" id="email" class="pit" placeholder="Enter Current Email">

            <label for="password" class="pil">Password</label>
            <input type="password" name="password" id="password" placeholder="Enter Current Password" class="pit">

            <input type="submit" value="Save Changes" class="pis">
        </form>

        <form action="/provider/profile/${id}/updatePassword" method="post" id="change-password-form">
            <h2>Change Password:</h2>
            <#if errorPassword??>
            <p class="error" style="color: red;">${errorPassword}</p>
            </#if>
            <#if successPassword??>
            <p class="success" style="color: green;">${successPassword}</p>
            </#if>
            <label for="current-password" class="cpl">Current Password:</label>
            <input type="password" name="current-password" placeholder="Enter Current Password" class="cpp">

            <label for="password" class="cpl">New Password:</label>
            <input type="password" name="password" id="" class="cpp" placeholder="Enter New Password">

            <label for="re-password" class="cpl">Re-enter New Password:</label>
            <input type="password" name="re-password" id="" placeholder="Re-enter New Password" class="cpp">

            <input type="submit" value="Save Changes" class="cps">
        </form>

        

    </div>

    <script>
        // Require typing YES to confirm sensitive delete action
        function confirmProviderDelete() {
            var input = prompt("Type YES to confirm account deletion. This cannot be undone.");
            return (input === "YES");
        }
    </script>
    
</body>
</html>