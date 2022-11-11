

// function updateLikeRequest(video_id, user_id, url) {
//     const liked = $('.like').attr('liked')
//     if (parseInt(1 - liked)) {
//         $.ajax({
//             type: "POST",
//             data: {
//                 video: video_id,
//                 user: user_id,
//             },
//             url: url,
//             success: function (data) {
//                 if (data.status != 200) {
//                     alert(data.error);
//                 } else {
//                     $('.like').attr('liked', 1);
//                     $('.dislike').attr('disliked', 0);
//                     $('.video-likes').text(parseInt($('.video-likes').text()) + 1);
//                     update_icon('like');
//                     update_icon('dislike');
//                 }
//             }
//         })
//     } else {
//         $.ajax({
//             type: "DELETE",
//             headers: {
//                 "X-CSRFToken": "{{ csrf_token }}",
//             },
//             data: {
//                 video: video_id,
//                 user: user_id,
//             },
//             url: url,
//             success: function (data) {
//                 if (data.status != 200) {
//                     alert(data.error);
//                 } else {
//                     $(".like").attr('liked', 0);
//                     $('.video-likes').text(parseInt($('.video-likes').text()) - 1);
//                     update_icon('like');
//                 }
//             }
//         })
//     }
// }

// function updateDislikeRequest(video_id, user_id, url) {
//     const disliked = $('.dislike').attr('disliked')
//     if (parseInt(1 - disliked)) {
//         $.ajax({
//             type: "POST",
//             headers: {
//                 "X-CSRFToken": "{{ csrf_token }}",
//             },
//             data: {
//                 video: video_id,
//                 user: user_id,
//             },
//             url: url,
//             success: function (data) {
//                 if (data.status != 200) {
//                     alert(data.error);
//                 } else {
//                     if ($(".like").attr('liked') == '1')
//                         $('.video-likes').text(parseInt($('.video-likes').text()) - 1);
//                     $(".dislike").attr('disliked', 1);
//                     $(".like").attr('liked', 0);
//                     update_icon('like');
//                     update_icon('dislike');
//                 }
//             }
//         })
//     } else {
//         $.ajax({
//             type: "DELETE",
//             headers: {
//                 "X-CSRFToken": "{{ csrf_token }}",
//             },
//             data: {
//                 video: video_id,
//                 user: user_id,
//             },
//             url: url,
//             success: function (data) {
//                 if (data.status != 200) {
//                     alert(data.error);
//                 } else {
//                     $(".dislike").attr('disliked', 0);
//                     update_icon('dislike');
//                 }
//             }
//         })
//     }
// }

// function updateViewRequest(video_id, user_id, url, time) {
//     $.ajax({
//         type: "POST",
//         headers: {
//             "X-CSRFToken": "{{ csrf_token }}",
//         },
//         data: {
//             video: video_id,
//             user: user_id,
//             time: time
//         },
//         url: url,
//         success: function (data) {
//             if (data.status != 200)
//                 alert(data.error);
//         }
//     })
// }