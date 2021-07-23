import { useState, useEffect } from "react";

export default function GithubDisplay({ login }) {
  //const [login, updateLogin] = useState("");
  const [data, updateData] = useState(null);
  //https://api.github.com/users/bpinkerton/repos
  const [repos, updateRepos] = useState(null);

  useEffect(() => {
    fetch(`https://api.github.com/users/${login}`)
      .then((response) => response.json())
      .then(updateData);
  }, [login]);

  useEffect(() => {
    if (data) {
      if (data.public_repos) {
        fetch(`https://api.github.com/users/${login}/repos`)
          .then((response) => response.json())
          .then(updateRepos);
      }
    }
  }, [data]);

  return data ? (
    <>
      <h1>{data.name}</h1>
      <span>
        <h3>{data.login}</h3>
      </span>
      <img src={data.avatar_url} alt={data.name} width="150"></img>
      <h2>Public Repositories:</h2>
      {repos ? (
        <ul>
          {repos.map((repo, i) => {
            return <li key={i}> {repo.name}</li>;
          })}
        </ul>
      ) : (
        <p>User does not have any public repositories.</p>
      )}
    </>
  ) : (
    <p>Loading data ...</p>
  );
}
