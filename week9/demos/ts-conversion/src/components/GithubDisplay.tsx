import { useState, useEffect } from "react";
import { githubUsers } from "../util/githubApi";

interface Props {
  login: string;
}

interface Data {
  name: string;
  login: string;
  avatar_url: string;
  type: any;
}

export default function GithubDisplay({ login }: Props) {
  const [data, updateData] = useState<Data | null>(null);

  useEffect(() => {
    githubUsers.get(login).then((response) => {
      updateData({
        name: response.data.name,
        login: response.data.login,
        avatar_url: response.data.avatar_url,
        type: response.data.type,
      });
    });
  }, [login]);

  return data ? (
    <>
      <h2>{data.name}</h2>
      <h3>
        {data.login} : {data.type}
      </h3>
      <img alt={data.name} src={data.avatar_url} width="200" />
    </>
  ) : (
    <p>Waiting on data.</p>
  );
}
