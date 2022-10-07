INSERT INTO photos (id, photo_data) VALUES(NEXTVAL('photo_sequence'), FILE_READ('src/main/resources/static/images/default.jpg'));

INSERT INTO books VALUES(NEXTVAL('book_sequence'), 'Трое в лодке, не считая собаки', 'Этот роман неоднократно экранизирован, в том числе и в нашей стране. Фильм, снятый на его основе, стал поистине культовым.
Идет время, сменяются эпохи, ног читатели по-прежнему не могут оторваться от совершенно невероятной история путешествия троих беззаботных английских джентльменов, пустившихся в плавание по Темзе вместе со своим любимцем — фокстерьером Монморанси.
Забавные недоразумения, веселые коллизии и полные комизма ситуации, из которых герои выходят, сохраняя истинно британское чувство собственного достоинства, и сегодня поражают своей оригинальностью и неувядающим юмором...', 1);
INSERT INTO books VALUES(NEXTVAL('book_sequence'), 'Волоколамское шоссе', 'Роман «Волоколамское шоссе» является наиболее известным и значимым произведением замечательного русского писателя-фронтовика Александра Бека. Этим романом о героических защитниках Москвы зачитывались в тылу, он был в полевых сумках бойцов на фронте. О книге во Франции писали как о шедевре, в Италии — как о самом выдающемся в русской литературе произведении о войне, а в Финляндии ее изучали в Военной академии. Роман был переведен на 10 европейских языков, а также на арабский и иврит. В этой книге вы найдете жестокую правду о самой страшной войне всех времен и народов.', 1);

--INSERT INTO files VALUES(NEXTVAL('file_sequence'),
--    'books_files/three_men_in_a_boat_to_say_nothing_of_the_dog', 1);

INSERT INTO genres VALUES(NEXTVAL('genre_sequence'),
    'Художественная литература', NULL);
INSERT INTO genres VALUES(NEXTVAL('genre_sequence'),
    'Исторический роман', 1);
INSERT INTO genres VALUES(NEXTVAL('genre_sequence'),
    'Классическая и современная проза', 1);
INSERT INTO genres VALUES(NEXTVAL('genre_sequence'),
    'Классика', 3);

INSERT INTO authors VALUES(NEXTVAL('author_sequence'),
    'Джером Дж. К.', '1859-05-02');
INSERT INTO authors VALUES(NEXTVAL('author_sequence'),
    'Александр Бек', '1903-01-03');

INSERT INTO books_genres VALUES(1, 1);
INSERT INTO books_genres VALUES(1, 3);
INSERT INTO books_genres VALUES(2, 1);
INSERT INTO books_genres VALUES(2, 2);

INSERT INTO books_authors VALUES(1, 1);
INSERT INTO books_authors VALUES(2, 2);
