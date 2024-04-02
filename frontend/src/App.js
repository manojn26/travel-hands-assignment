import { BrowserRouter, Route, Routes } from "react-router-dom";
import "./App.css";
import TradeDetails from "./TradeDetails";
import OrderDetails from "./OrderDetails";

function App() {
  return (
    <div>
      <BrowserRouter>
        <Routes>
          <Route path='/' element={<TradeDetails />} />
          <Route path='/orders' element={<OrderDetails />} />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
