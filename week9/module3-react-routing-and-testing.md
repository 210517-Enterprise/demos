# Module 3: React Routing and Testing
## 1. Routing
1. ReactRouter
    >  npm install react-router@next react-router-dom@next
    1. Create a pages.js file to house our pages
        - in pages.js, create several functions to export and use in our router
    2. Configure the router in index.js
        > import {BrowserRouter as Router} from 'react-router-dom';
        > 
        > Wrap the App component in the Router component so that it is accessible through the entire application
    3. In the App component, import { Routes, Route } from 'react-router-dom'
    4. In the App component, import our functions from the pages file
    5. Add the following code to configure our Routes

            export default function App() {

                return (
                    <>
                    <Routes>
                        <Route path="/" element={<Home />}></Route>
                        <Route path="/about" element={<About />}></Route>
                        <Route path="/contact" element={<Contact />}></Route>
                    </Routes>
                    </>
                );
            }
2. Incorporate the link component
    > import { Link } from 'react-router-dom'; in our pages.js file
    - The Link component allows us to create links to other components within out application by accessing the router.

            <Link to="/">Home</Link>
            <Link to="/about">About</Link>
            <Link to="/schedule">Schedule</Link>
                        
3. Nesting Links
    > import { Link, Outlet } from 'react-router-dom';

            <Routes>
                <Route path="/" element={<Home />} />
                <Route path="/about" element={<About />} />
                <Route path="/schedule" element={<Schedule />}>
                    <Route path="/secret" element={<Secret showHidden={true}/>} />
                </Route>
                <Route path="/secret" element={<Secret showHidden={false}/>} />
            </Routes>
##  2. Testing in React with Jest and React Testing Library
    - Create a new file to house our tests.
    > Note: the convention for test files is to end them with the .test.js extension so they can be picked up by the test runner.

            import {timesTwo} from './functions';
            test("Multiplies by two", () => {
                expect(timesTwo(4)).toBe(8);
            })

    - Using TDD, lets go back and write a function called timesTwo that will satisfy our test.
    2. Testing Library
        > npm install --save-dev @testing-library/react
        - Helps us to render elements to test our UI and ensure basic functionality

                import { render } from "@testing-library/react";
                import React from "react";
                import App from "./App";

                test("renders an h1", () => {
                    const { getByText } = render(<App />)
                    const h1 = getByText(/Hello React Testing Library/);
                    expect(h1).toHaveTextContent("Hello React Testing Library");
                })
## 3. Using TypeScript in React
    > npm install typescript
    - TypeScript allows us to add more type safety to our React code.
    - With more type safety comes more complexity.
    - Prior to bringing in TS, our props could be any data that we decided to send into the component, 
    causing us to have to hevily rely on conditional rendering to ensure functionality. 
    - With TS, we can now specify the exact types expected within the properties.
