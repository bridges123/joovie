import {get_dt_counts} from "../perform_data.js";


for (let comment_dt of document.querySelectorAll('.comment-created')) {
    const dateTime = new Date(comment_dt.textContent.trim());
    comment_dt.textContent = get_dt_counts(dateTime);
}