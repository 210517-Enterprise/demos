import { Navbar, Nav } from "react-bootstrap";

export default function AppMenu() {
  return (
    <>
      <Navbar expand="sm" bg="dark" variant="dark">
        <Navbar.Brand href="/">Home</Navbar.Brand>
        <Nav>
          <Nav.Link href="/github">Github</Nav.Link>
          <Nav.Link href="/pokemon">Pokemon</Nav.Link>
        </Nav>
      </Navbar>
    </>
  );
}
