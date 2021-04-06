insert into user (id, name) values (1, 'Tony Montana');

insert into vehicle (id, description) values (1, 'Kit');

insert into ride (user_id, vehicle_id, from_point, to_point, rating) values (1, 1, 'Athens', 'Rome', 5);

insert into question (id, description, type) values (1, 'Question ONE', 'MULTIPLE_CHOICE');

insert into choice (id, description, question_id) values (1, 'choice A', 1);
insert into choice (id, description, question_id) values (2, 'choice B', 1);
insert into choice (id, description, question_id) values (3, 'choice C', 1);

insert into survey (id, name) values (1, 'First Survey');

insert into surveys_questions (survey_id, question_id) values (1, 1);

insert into survey_response (id, survey_id, user_id) values (1, 1, 1);

insert into answer (id, question_id) values (1, 1);

insert into answers_choices (answer_id, choice_id) values (1, 2);
insert into answers_choices (answer_id, choice_id) values (1, 3);

insert into survey_responses_answers (survey_response_id, answer_id) values (1, 1);