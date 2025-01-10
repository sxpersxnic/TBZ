import { Post, UsersTable } from "./definitions";
import { getUuid } from "./utils";

//TODO: Remove when auth is implemented
/**
 * Description placeholder
 *
 * @type {UsersTable[]}
 */
const dummyUser: UsersTable[] = [ 
  {
    id: '11111111-1111-4111-b111-111111111111',
    username: 'testuser',
    email: 'user@localhost.com',
    image_url: '/user/default-32x32.png',
    password: 'P@ssw0rd!14',
  },
]

// Dummy records: 6
/**
 * Description placeholder
 *
 * @type {UsersTable[]}
 */
const users: UsersTable[] = [
  {
    id: getUuid(),
    username: 'Evil Rabbit',
    email: 'evil@rabbit.com',
    image_url: '/user/evil-rabbit.png',
    password: '123456',
  },
  {
    id: getUuid(),
    username: 'Delba de Oliveira',
    email: 'delba@oliveira.com',
    image_url: '/user/delba-de-oliveira.png',
    password: '123456',
  },
  {
    id: getUuid(),
    username: 'Lee Robinson',
    email: 'lee@robinson.com',
    image_url: '/user/lee-robinson.png',
    password: '123456',
  },
  {
    id: getUuid(),
    username: 'Michael Novotny',
    email: 'michael@novotny.com',
    image_url: '/user/michael-novotny.png',
    password: '123456',
  },
  {
    id: getUuid(),
    username: 'Amy Burns',
    email: 'amy@burns.com',
    image_url: '/user/amy-burns.png',
    password: '123456',
  },
  {
    id: getUuid(),
    username: 'Balazs Orban',
    email: 'balazs@orban.com',
    image_url: '/user/balazs-orban.png',
    password: '123456',
  },
];

// Dummy records: 12
/**
 * Description placeholder
 *
 * @type {Post[]}
 */
const posts: Post[] = [
  {
    id: getUuid(),
    author_id: users[0].id,
    title: 'Post 1',
    body: 'This dummy post nr. 1',
    date: '2024-06-12',
  },
  {
    id: getUuid(),
    author_id: users[0].id,
    title: 'Post 2',
    body: 'This dummy post nr. 2',
    date: '2024-08-10',
  },
  {
    id: getUuid(),
    author_id: users[1].id,
    title: 'Post 3',
    body: 'This dummy post nr. 3',
    date: '2024-05-12',
  },
  {
    id: getUuid(),
    author_id: users[1].id,
    title: 'Post 4',
    body: 'This dummy post nr. 4',
    date: '2024-06-01',
  },
  {
    id: getUuid(),
    author_id: users[2].id,
    title: 'Post 5',
    body: 'This dummy post nr. 5',
    date: '2023-12-10',
  },
  {
    id: getUuid(),
    author_id: users[2].id,
    title: 'Post 6',
    body: 'This dummy post nr. 6',
    date: '2023-04-12',
  },
  {
    id: getUuid(),
    author_id: users[3].id,
    title: 'Post 7',
    body: 'This dummy post nr. 7',
    date: '2024-06-02',
  },
  {
    id: getUuid(),
    author_id: users[3].id,
    title: 'Post 8',
    body: 'This dummy post nr. 8',
    date: '2024-06-03',
  },
  {
    id: getUuid(),
    author_id: users[4].id,
    title: 'Post 9',
    body: 'This dummy post nr. 9',
    date: '2024-06-11',
  },
  {
    id: getUuid(),
    author_id: users[4].id,
    title: 'Post 10',
    body: 'This dummy post nr. 10',
    date: '2024-08-12',
  },
  {
    id: getUuid(),
    author_id: users[5].id,
    title: 'Post 11',
    body: 'This dummy post nr. 11',
    date: '2024-07-12',
  },
  {
    id: getUuid(),
    author_id: users[5].id,
    title: 'Post 12',
    body: 'This dummy post nr. 12',
    date: '2024-06-06',
  },
];

/**
 * Description placeholder
 *
 * @type {{}}
 */
const revenue = [
  { month: 'Jan', revenue: 2000 },
  { month: 'Feb', revenue: 1800 },
  { month: 'Mar', revenue: 2200 },
  { month: 'Apr', revenue: 2500 },
  { month: 'May', revenue: 2300 },
  { month: 'Jun', revenue: 3200 },
  { month: 'Jul', revenue: 3500 },
  { month: 'Aug', revenue: 3700 },
  { month: 'Sep', revenue: 2500 },
  { month: 'Oct', revenue: 2800 },
  { month: 'Nov', revenue: 3000 },
  { month: 'Dec', revenue: 4800 },
];

export { dummyUser, users, posts, revenue };
