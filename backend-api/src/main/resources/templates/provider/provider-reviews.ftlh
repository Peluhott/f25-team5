<#ftl encoding="UTF-8">

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8" />
  <meta name="viewport" content="width=device-width,initial-scale=1" />
  <title>Provider Reviews</title>

  <link rel="stylesheet" href="/providers/css/style.css">
  <link rel="stylesheet" href="https://site-assets.fontawesome.com/releases/v7.0.0/css/all.css"/>
</head>
<body>

  <aside>
    <ul>
      <a href="/provider/home/${providerId}" class="sidebtn"><li><i class="fa-solid fa-house"></i></li></a>
      <a href="/provider/${providerId}/notifications" class="sidebtn"><li><i class="fa-solid fa-bell"></i></li></a>
      <a href="/provider/profile/${providerId}" class="sidebtn"><li><i class="fa-solid fa-user"></i></li></a>

      <div id="break"></div>

      <a href="/provider/${providerId}/reviews" class="sidebtn"><li><i class="fa-solid fa-envelope"></i></li></a>
      <a href="/provider/${providerId}/applications" class="sidebtn"><li><i class="fa-solid fa-people-group"></i></li></a>

      <a href="/provider/login" class="sidebtn"><li><i class="fa-solid fa-door-open"></i></li></a>
    </ul>
  </aside>

  <header>
    <img src="/customer/images/logo-transparent.png" id="logo" alt="GroupLink logo">
    <h1>Reviews</h1>
  </header>

  <div class="main-content">
    <section class="reviews-container">
      <#if (reviews?size == 0)>
        <div class="empty-state">
          <p>No reviews yet.</p>
        </div>
      <#else>
        <#list reviews as review>
          <article class="review-card">
            <div class="review-top">
              <div class="rating">
                <#-- render stars for integer rating (fallback to numeric) -->
                <#if review.rating??>
                  <#list 1..(review.rating?int) as i>
                    <i class="fa-solid fa-star"></i>
                  </#list>
                  <span class="rating-number">(${review.rating})</span>
                <#else>
                  <span class="rating-number">No rating</span>
                </#if>
              </div>

              <div class="review-meta">
                <span class="review-author">${(review.customer.username)!'Anonymous'}</span>
                <#if review.createdAt??>
                  <span class="review-date">${review.createdAt?string("yyyy-MM-dd")}</span>
                </#if>
              </div>
            </div>

            <#-- CUSTOMER PICTURE -->
            <div class="review-author-pic" style="margin:10px 0;">
              <img
                src="/customer/images/${(review.customer.profilePicturePath)!'nullpfp.jpg'}"
                alt="${(review.customer.username)!'Reviewer'}"
                style="width:56px;height:56px;object-fit:cover;border-radius:6px;"
                onerror="this.src='/customer/images/nullpfp.jpg'">
            </div>

            <div class="review-body">
              <p class="review-message">${(review.message)!''}</p>
            </div>

            <#-- PROVIDER RESPONSE / REPLY AREA -->
            <#if review.response?? && (review.response?has_content)>
              <div class="provider-response" style="display:flex;gap:10px;margin-top:12px;align-items:flex-start;">
                <div class="provider-pic">
                  <img
                    src="/provider/images/${review.provider.profilePicturePath}"
                    alt="Provider"
                >
                </div>
                <div class="provider-response-text">
                  <strong>Your response</strong>
                  <p>${review.response}</p>
                </div>
              </div>
            <#else>
              <form
                action="/provider/${providerId}/reviews/${(review.id)!}/reply"
                method="post"
                class="reply-form"
                style="margin-top:12px;"
              >
                <label for="response_${review?index}">
                  <strong>Reply to this review</strong>
                </label>
                <textarea
                  id="response_${review?index}"
                  name="response"
                  rows="3"
                  placeholder="Write a response..."
                  required
                  style="width:100%;margin-top:6px;"
                ></textarea>
                <div class="form-actions" style="margin-top:8px;">
                  <button type="submit" class="btn">Send reply</button>
                </div>
              </form>
            </#if>
          </article>
        </#list>
      </#if>
    </section>
  </div>

</body>
</html>
