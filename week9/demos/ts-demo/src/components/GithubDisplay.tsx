import { useState, useEffect } from "react";
import { githubUsers } from "../util/githubApi";

interface Props {
  username: string;
}

interface Data {
  name: string;
  login: string;
  avatar_url: string;
}

export default function GithubDisplay({ username }: Props) {
  const [data, updateData] = useState<Data | null>(null);

  useEffect(() => {
    githubUsers.get(username).then((response) => {
      updateData({
        name: response.data.name,
        login: response.data.login,
        avatar_url: response.data.avatar_url,
      });
    });
  }, [username]);

  return data ? (
    <>
      <h2>{data.name}</h2>
      <h4>Username: {data.login}</h4>
      <img alt={data.name} src={data.avatar_url} width="200" />
    </>
  ) : (
    <p>Waiting on Data</p>
  );
}
