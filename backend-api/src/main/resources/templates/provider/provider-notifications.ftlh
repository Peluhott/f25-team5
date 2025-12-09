<#ftl encoding="UTF-8">

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8" />
  <meta name="viewport" content="width=device-width,initial-scale=1" />
  <title>Notifications</title>

  <link rel="stylesheet" href="/providers/css/style.css">
  <link rel="stylesheet" href="https://site-assets.fontawesome.com/releases/v7.0.0/css/all.css"/>
</head>
<body>

  <aside>
    <ul>
      <a href="/provider/home/${providerId}" class="sidebtn" id="home-page"><li><i class="fa-solid fa-house"></i></li></a>
      <a href="/provider/${providerId}/notifications" class="sidebtn" id="notification-link"><li><i class="fa-solid fa-bell"></i></li></a>
      <a href="/provider/profile/${providerId}" class="sidebtn" id="people-link"><li><i class="fa-solid fa-user"></i></li></a>

      <div id="break"></div>

      <a href="/provider/${providerId}/reviews" class="sidebtn" id="reviews-link"><li><i class="fa-solid fa-envelope"></i></li></a>
      <a href="/provider/group/${providerId}" class="sidebtn" id="group-link"><li><i class="fa-solid fa-people-group"></i></li></a>

      <a href="/provider/login" class="sidebtn" id="logout"><li><i class="fa-solid fa-door-open"></i></li></a>
    </ul>
  </aside>

  <header>
    <img src="/customer/images/logo-transparent.png" id="logo" alt="GroupLink logo">
    <h1>Notifications</h1>
  </header>

  <div class="main-content">
    <section class="notifications-list">
      <#if (notifications?size == 0)>
        <div class="empty-state">
          <p>No notifications.</p>
        </div>
      <#else>
        <#list notifications as notification>
          <article class="noti-container <#if notification.read?? && notification.read>read<#else>unread</#if>">
            <div class="notification-left" style="display:flex;gap:12px;align-items:flex-start;">
              <div class="notification-type" style="flex:0 0 40px;">
                <i class="fa-solid fa-bell" style="font-size:18px;"></i>
              </div>
              <div style="flex:1;">
                <div style="display:flex;justify-content:space-between;align-items:center;">
                  <div>
                    <strong>${notification.type! 'Notification'}</strong>
                    <div style="color:#666;font-size:0.9rem;">${notification.message! ''}</div>
                  </div>
                  <div style="color:#999;font-size:0.85rem;">
                    <#if notification.timestamp??>${notification.timestamp}</#if>
                  </div>
                </div>
              </div>
            </div>

            <div class="notification-actions" style="margin-top:8px;display:flex;gap:8px;">
              <form action="/provider/${providerId}/notifications/${notification.id}/markRead" method="post" style="display:inline">
                <button type="submit" class="btn small">
                  <#if notification.read?? && notification.read>Mark unread<#else>Mark read</#if>
                </button>
              </form>

              <form action="/provider/${providerId}/notifications/${notification.id}/delete" method="post" style="display:inline">
                <button type="submit" class="btn danger small" onclick="return confirm('Delete this notification?');">Delete</button>
              </form>
            </div>
          </article>
        </#list>
      </#if>
    </section>
  </div>

</body>
</html>