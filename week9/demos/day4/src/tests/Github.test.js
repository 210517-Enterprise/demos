import GithubDisplay from "../components/GithubDisplay";
import { render, screen } from "@testing-library/react";

describe("Tests to ensure proper rendering of the Github component", () => {
  it('Displays "Loading Data ... " when loading or user has not submitted their login', async () => {
    const {} = render(<GithubDisplay />);
    expect(await screen.findByText("Loading data ...")).toBeVisible();
  });

  it("Displays the correct user info when a valid login is received", async () => {
    const user = "bpinkerton";
    const fullName = "Brandon Pinkerton";
    const { getByText } = render(<GithubDisplay login={user} />);
    expect(await screen.findByText(user)).toBeVisible();
    const username = getByText(user);
    const name = getByText(fullName);

    expect(username).toHaveTextContent(user);
    expect(name).toHaveTextContent(fullName);
  });
});
