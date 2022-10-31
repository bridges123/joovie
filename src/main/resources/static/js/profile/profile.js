import {get_dt_counts} from "../perform_dt.js";

for (let video of document.querySelectorAll('.apps.video-datetime')) {
    const dateTime = new Date(video.textContent.trim());
    video.textContent = get_dt_counts(dateTime);
}