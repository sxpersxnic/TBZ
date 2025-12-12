import { Route, Routes } from 'react-router-dom';
import AddRecipe from './components/AddRecipe/AddRecipe';
import Browse from './components/Browse/Browse';
import EditRecipe from './components/EditRecipe/EditRecipe';
import MyNavbar from './components/MyNavbar/MyNavbar';
import Planer from './components/Planer/Planer';

function App() {
	return (
		<>
			<MyNavbar />
			<div>
				<Routes>
					<Route
						path='/'
						element={<Browse />}
					/>
					<Route
						path='/planer'
						element={<Planer />}
					/>
					<Route
						path='/new-menues'
						element={<AddRecipe />}
					/>
					<Route
						path='/edit-recipe/:recipeId'
						element={<EditRecipe />}
					/>
				</Routes>
			</div>
		</>
	);
}

export default App;

