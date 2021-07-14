import { Routes, Route } from "react-router-dom";

import HelloWorld from "./HelloWorld";
import Github from "./Github";
import Pokemon from "./Pokemon";

export default function AppRouter() {
  return (
    <>
      <Routes>
        <Route
          path="/"
          element={<HelloWorld noun="World" location="React Router" />}
        />
        <Route path="/github" element={<Github />} />
        <Route path="/pokemon" element={<Pokemon />} />
      </Routes>
    </>
  );
}
