import {performFollowersCount, performVideoCount} from "../perform_data.js";


performFollowersCount('channel-followers');

performVideoCount('channel-video-count');

$('.follow-button').click(function() {
    const followed = $(this).toggleClass("followed").hasClass("followed");
    if (followed) {
        $(this).text("Отписаться");
    } else {
        $(this).text("Отслеживать");
    }
    // request
})