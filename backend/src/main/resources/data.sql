-- User roles
INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');

-- Users - password 5EtH^8e^u6
INSERT INTO users(id, username, password , email) VALUES(1, "testuser", "$2a$10$V7H83YZiT7g/lHP12m8PReKks9SrY.hyM0QmZYPVm7DhiyIL2VkI6", "test@email.com");
INSERT INTO user_roles(user_id, role_id) VALUES(1,1);
-- INSERT INTO user_roles(user_id, role_id) VALUES(1,2);

-- Recipe 1
INSERT INTO recipe(id, name,servings, vegetarian) VALUES(1,'Recipe1', 4 , 1);
INSERT INTO ingredient(id, name, amount) VALUES(10, 'salt', '10 gr');
INSERT INTO ingredient(id, name, amount) VALUES(11, 'peper', '15 gr');
INSERT INTO ingredient(id, name, amount) VALUES(12, 'flour', '120 gr');
INSERT INTO recipe_ingredients(recipe_id, ingredients_id) VALUES(1,10);
INSERT INTO recipe_ingredients(recipe_id, ingredients_id) VALUES(1,11);
INSERT INTO recipe_ingredients(recipe_id, ingredients_id) VALUES(1,12);

-- Recipe 2
INSERT INTO recipe(id, name,servings, vegetarian) VALUES(2,'Recipe2', 3 , 0);
INSERT INTO ingredient(id, name, amount) VALUES(20, 'peper', '3 gr');
INSERT INTO ingredient(id, name, amount) VALUES(21, 'water', '150 gr');
INSERT INTO ingredient(id, name, amount) VALUES(22, 'herbs', '12 gr');
INSERT INTO ingredient(id, name, amount) VALUES(23, 'butter', '40 gr');
INSERT INTO recipe_ingredients(recipe_id, ingredients_id) VALUES(2,20);
INSERT INTO recipe_ingredients(recipe_id, ingredients_id) VALUES(2,21);
INSERT INTO recipe_ingredients(recipe_id, ingredients_id) VALUES(2,22);
INSERT INTO recipe_ingredients(recipe_id, ingredients_id) VALUES(2,23);