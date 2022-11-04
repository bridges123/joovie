import {get_dt_counts} from "../perform_data.js";

for (let video of document.querySelectorAll('.video-datetime')) {
    const dateTime = new Date(video.textContent.trim());
    video.textContent = get_dt_counts(dateTime);
}