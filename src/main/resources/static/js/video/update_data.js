// function update_like(video_id, user_id, url) {
//     const liked = $('.like').attr('liked')
//     if (parseInt(1 - liked)) {
//         $.ajax({
//             type: "POST",
//             data: {
//                 apps.video: video_id,
//                 apps.user: user_id,
//             },
//             url: url,
//             success: function (data) {
//                 if (data.status != 200) {
//                     alert(data.error);
//                 } else {
//                     $('.like').attr('liked', 1);
//                     $('.dislike').attr('disliked', 0);
//                     $('.apps.video-likes').text(parseInt($('.apps.video-likes').text()) + 1);
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
//                 apps.video: video_id,
//                 apps.user: user_id,
//             },
//             url: url,
//             success: function (data) {
//                 if (data.status != 200) {
//                     alert(data.error);
//                 } else {
//                     $(".like").attr('liked', 0);
//                     $('.apps.video-likes').text(parseInt($('.apps.video-likes').text()) - 1);
//                     update_icon('like');
//                 }
//             }
//         })
//     }
// }
//
// function update_dislike(video_id, user_id, url) {
//     const disliked = $('.dislike').attr('disliked')
//     if (parseInt(1 - disliked)) {
//         $.ajax({
//             type: "POST",
//             headers: {
//                 "X-CSRFToken": "{{ csrf_token }}",
//             },
//             data: {
//                 apps.video: video_id,
//                 apps.user: user_id,
//             },
//             url: url,
//             success: function (data) {
//                 if (data.status != 200) {
//                     alert(data.error);
//                 } else {
//                     if ($(".like").attr('liked') == '1')
//                         $('.apps.video-likes').text(parseInt($('.apps.video-likes').text()) - 1);
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
//                 apps.video: video_id,
//                 apps.user: user_id,
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
//
// function update_view(video_id, user_id, url, time) {
//     $.ajax({
//         type: "POST",
//         headers: {
//             "X-CSRFToken": "{{ csrf_token }}",
//         },
//         data: {
//             apps.video: video_id,
//             apps.user: user_id,
//             time: time
//         },
//         url: url,
//         success: function (data) {
//             if (data.status != 200)
//                 alert(data.error);
//         }
//     })
// }