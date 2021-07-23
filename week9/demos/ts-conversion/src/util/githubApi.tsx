import axios from "axios";

const githubUsers = axios.create({
  baseURL: "https://api.github.com/users/",
});

export { githubUsers };
