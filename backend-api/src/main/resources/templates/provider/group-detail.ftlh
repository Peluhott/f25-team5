<#ftl encoding="UTF-8">

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8" />
  <meta name="viewport" content="width=device-width,initial-scale=1" />
  <title>Group — ${group.name! "Group"}</title>

  <link rel="stylesheet" href="/providers/css/style.css">
  <link rel="stylesheet" href="https://site-assets.fontawesome.com/releases/v7.0.0/css/all.css"/>
</head>
<body>

  <aside>
    <ul>
      <a href="/provider/home/${providerId}" class="sidebtn" id="home-page"><li><i class="fa-solid fa-house"></i></li></a>
      <a href="#" class="sidebtn" id="notification-link"><li><i class="fa-solid fa-bell"></i></li></a>
      <a href="/provider/profile/${providerId}" class="sidebtn" id="people-link"><li><i class="fa-solid fa-user"></i></li></a>

      <div id="break"></div>

      <a href="/provider/${providerId}/reviews" class="sidebtn" id="reviews-link"><li><i class="fa-solid fa-envelope"></i></li></a>
      <a href="/provider/${providerId}/applications" class="sidebtn" id="group-link"><li><i class="fa-solid fa-people-group"></i></li></a>

      <a href="/provider/login" class="sidebtn" id="logout"><li><i class="fa-solid fa-door-open"></i></li></a>
    </ul>
  </aside>

  <header>
    <img src="/customer/images/logo-transparent.png" id="logo" alt="GroupLink logo">
    <h1>GroupLink</h1>
  </header>

  <div class="main-content">

    <div class="group-detail-top">
      <div class="group-title-block">
        <h2 class="group-name">${group.name! "Untitled Group"}</h2>
        <a class="btn edit-btn" href="/provider/${providerId}/group/${group.id}/edit">Edit Group</a>
      </div>
    </div>

    <section class="group-detail-card">
      <div class="group-image-wrap">
        <#-- show stored path or a default image -->
        <img class="group-image" src="/${group.profilePicturePath!''}" alt="${group.name!''}" onerror="this.src='/groups/images/sampleGroupPoster.webp'">
      </div>

      <div class="group-info">
        <dl>
          <dt>Name</dt>
          <dd>${group.name! "—"}</dd>

          <dt>Type</dt>
          <dd>${group.type! "—"}</dd>

          <dt>Location</dt>
          <dd>${group.location! "—"}</dd>

          <dt>Max Members</dt>
          <dd>${group.maxMem!0}</dd>

          <dt>Active Members</dt>
          <dd>${group.activeMem!0}</dd>

          <dt>Active</dt>
          <dd><#if group.active?? && group.active>0>Yes<#else>No</#if></dd>

          <dt>Profile Picture Path</dt>
          <dd>${group.profilePicturePath! "—"}</dd>
        </dl>

        <div class="group-content-block">
          <h3>About</h3>
          <#if group.content?? && group.content?has_content>
            <p class="group-content">${group.content}</p>
          <#else>
            <p class="group-content muted">No description provided.</p>
          </#if>
        </div>
      </div>
    </section>

    <#-- Members list with remove buttons (POST to remove endpoint) -->
   <section class="group-section members-section">
  <h3>Members (${(memberList?size)!0})</h3>

  <#if memberList?? && (memberList?size > 0)>
    <ul class="member-list">
      <#list memberList as membership>
        <li class="member-item">
          <img class="member-pfp"
               src="/customer/images/${membership.customer.profilePicturePath}"
               alt="pfp"
               >

          <div class="member-info">
            <div class="member-name">${(membership.customer.username)!'Unknown'}</div>
            <div class="member-email">${(membership.customer.email)!''}</div>
          </div>

          <form method="post"
                action="/application/remove"
                style="display:inline">
            <input type="hidden" name="membershipId" value="${membership.id}">
            <input type="hidden" name="status" value="inactive">
            <input type="hidden" name="providerId" value="${providerId}">
            <button type="submit" class="btn remove-btn">Remove</button>
          </form>
        </li>
      </#list>
    </ul>
  <#else>
    <p class="muted">No members.</p>
  </#if>
</section>


  </div>

</body>
</html>