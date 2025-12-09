<#ftl encoding="UTF-8">

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Group — ${group.name! "Group"}</title>

    <link rel="stylesheet" href="/providers/css/style.css">
    <script src="https://kit.fontawesome.com/b69a734b4d.js" crossorigin="anonymous"></script>
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
        <img src="/customer/images/logo-transparent.png" alt="" id="logo">
        <h1>Edit Group</h1>
    </header>

    <div class="main-content">
        <div class="group-create">
            <form action="/provider/${providerId}/group/${group.id}/update"
                  method="post"
                  enctype="multipart/form-data"
                  class="create-group-form">

                <input type="hidden" name="providerId" value="${providerId!}">

                <label>Group Name:</label>
                <input type="text" name="name" value="${group.name!''}" required>

                <label>Location:</label>
                <input type="text" name="location" value="${group.location!''}" required>

                <label>Type:</label>
                <select name="type" required>
                    <option value="">Select type...</option>
                    <option value="Hackathon" <#if group.type == "Hackathon">selected</#if>>Hackathon</option>
                    <option value="Gaming"   <#if group.type == "Gaming">selected</#if>>Gaming</option>
                    <option value="Sports"    <#if group.type == "Sports">selected</#if>>Sports</option>
                    <option value="Volunteer"   <#if group.type == "Volunteer">selected</#if>>Volunteer</option>
                    <option value="Study"     <#if group.type == "Study">selected</#if>>Study</option>


                    <#-- allow existing custom type to appear as selected -->
                    <#if group.type?? &&
                         (group.type != "Hackathon" &&
                          group.type != "Study" &&
                          group.type != "Gaming" &&
                          group.type != "Sports" &&
                          group.type != "Volunteer")>
                        <option value="${group.type}" selected>${group.type}</option>
                    </#if>
                </select>

                <label>Max Members:</label>
                <input type="number" name="maxMem" min="1" value="${group.maxMem!1}" required>

                <label>Content / Description:</label>
                <textarea name="content" rows="5">${group.content!''}</textarea>

                <label>Current Picture:</label>
                <div class="current-picture">
                    <img src="/${group.profilePicturePath!''}"
                         alt="Group picture"
                         onerror="this.src='/groups/images/sampleGroupPoster.webp'"
                         class="preview-image">
                </div>

                <label>Change Profile Picture:</label>
                <input type="file" name="picture" accept="image/*">

                <div class="form-actions">
                    <button type="submit" class="btn">Save Changes</button>
                </div>
            </form>

            <form action="/provider/${providerId}/group/${group.id}/delete"
                  method="post"
                  onsubmit="return confirm('Delete this group? This action cannot be undone.');"
                  style="margin-top:16px;">
                <input type="hidden" name="providerId" value="${providerId!}">
                <button type="submit" class="btn danger">Delete Group</button>
            </form>
        </div>
    </div>

</body>
</html>
