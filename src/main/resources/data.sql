INSERT INTO hov_user (user_id, name, email, password) VALUES(4, 'tom', 'aaa', 'aaa');
INSERT INTO hov_user (user_id, name, email, password) VALUES(5, 'peter', 'bbb', 'bbb');
INSERT INTO hov_user (user_id, name, email, password) VALUES(8, 'sss', 'sedoh', 'sss');
INSERT INTO hov_user (user_id, name, email, password) VALUES(9, 'sss', 'test11', 'sss');
INSERT INTO hov_user (user_id, name, email, password) VALUES(10, 'qqq', 'john.doe@example.com', 'qqq');
INSERT INTO hov_user (user_id, name, email, password) VALUES(11, 'tomy', 'tony@example.com', '111');
INSERT INTO hov_user (user_id, name, email, password) VALUES(12, '040', 'tomato@example.com', 'tomato040');
INSERT INTO hov_user (user_id, name, email, password) VALUES(13, '040', 'potato@example.com', 'tomato040');
INSERT INTO hov_user (user_id, name, email, password) VALUES(14, 'TPI', 'TPI@example.com', '666');
INSERT INTO hov_user (user_id, name, email, password) VALUES(15, 'TPI', 'TPallstart@example.com', '666');
INSERT INTO hov_user (user_id, name, email, password) VALUES(16, 'TPI', 'llstart@example.com', '666');
INSERT INTO hov_user (user_id, name, email, password) VALUES(17, 'TPI', 'start@example.com', '666');



INSERT INTO `role` (id, role_name, role_name_chinese) VALUES(100, 'Passenger', '乘客');
INSERT INTO `role` (id, role_name, role_name_chinese) VALUES(200, 'Driver', '司機');
INSERT INTO `role` (id, role_name, role_name_chinese) VALUES(900, 'Admin', '管理員');


INSERT INTO user_role_map (urm_id, user_id, role_id) VALUES(4, 10, 100);
INSERT INTO user_role_map (urm_id, user_id, role_id) VALUES(5, 11, 100);
INSERT INTO user_role_map (urm_id, user_id, role_id) VALUES(6, 9, 100);
INSERT INTO user_role_map (urm_id, user_id, role_id) VALUES(11, 10, 200);
INSERT INTO user_role_map (urm_id, user_id, role_id) VALUES(12, 12, 100);
INSERT INTO user_role_map (urm_id, user_id, role_id) VALUES(13, 12, 200);
INSERT INTO user_role_map (urm_id, user_id, role_id) VALUES(14, 9, 200);
INSERT INTO user_role_map (urm_id, user_id, role_id) VALUES(15, 13, 100);
INSERT INTO user_role_map (urm_id, user_id, role_id) VALUES(16, 14, 100);
INSERT INTO user_role_map (urm_id, user_id, role_id) VALUES(17, 15, 100);
INSERT INTO user_role_map (urm_id, user_id, role_id) VALUES(18, 15, 200);
INSERT INTO user_role_map (urm_id, user_id, role_id) VALUES(19, 16, 100);
INSERT INTO user_role_map (urm_id, user_id, role_id) VALUES(20, 17, 100);


INSERT INTO carpool (id, driver_Id, launch_time, site, destination, pick_AMT, order_AMT, is_cancel, create_time) VALUES(1, 4, '2024-11-18 08:00:00', 'Taipei', 'Hsinchu', 3, 0, 'N', '');
INSERT INTO carpool (id, driver_Id, launch_time, site, destination, pick_AMT, order_AMT, is_cancel, create_time) VALUES(2, 5, '2024-11-18 09:30:00', 'Hsinchu', 'Taichung', 2, 0, 'N', '');
INSERT INTO carpool (id, driver_Id, launch_time, site, destination, pick_AMT, order_AMT, is_cancel, create_time) VALUES(3, 5, '2024-11-18 11:00:00', 'Taichung', 'Kaohsiung', 1, 0, 'N', '');
INSERT INTO carpool (id, driver_Id, launch_time, site, destination, pick_AMT, order_AMT, is_cancel, create_time) VALUES(4, 5, '2024-11-18 14:00:00', 'Taipei', 'Kaohsiung', 5, 0, 'Y', '');
INSERT INTO carpool (id, driver_Id, launch_time, site, destination, pick_AMT, order_AMT, is_cancel, create_time) VALUES(5, 4, '2024-11-18 16:30:00', 'Kaohsiung', 'Tainan', 4, 0, 'N', '');
INSERT INTO carpool (id, driver_Id, launch_time, site, destination, pick_AMT, order_AMT, is_cancel, create_time) VALUES(12, 11, '2024-11-30 08:00:00', 'Hualan', 'Ilan', 3, 0, 'N', '2024-11-28 16:12:30');
INSERT INTO carpool (id, driver_Id, launch_time, site, destination, pick_AMT, order_AMT, is_cancel, create_time) VALUES(13, 12, '2024-12-06 12:30:00', 'Taipei', 'Taoyun', 2, 1, 'N', '2024-11-29 22:35:45');
INSERT INTO carpool (id, driver_Id, launch_time, site, destination, pick_AMT, order_AMT, is_cancel, create_time) VALUES(14, 15, '2024-12-06 12:30:00', 'Taipei', 'Taichuang', 5, 0, 'Y', '2024-12-03 15:08:00');


INSERT INTO carpool_map (cmId, carpool_id, user_id, order_time, is_cancel) VALUES(78, 14, 14, '2024-12-04 11:09:53', 'Y');
INSERT INTO carpool_map (cmId, carpool_id, user_id, order_time, is_cancel) VALUES(79, 14, 14, '2024-12-04 19:28:19', 'Y');
INSERT INTO carpool_map (cmId, carpool_id, user_id, order_time, is_cancel) VALUES(80, 14, 14, '2024-12-04 20:00:17', 'Y');
INSERT INTO carpool_map (cmId, carpool_id, user_id, order_time, is_cancel) VALUES(81, 14, 13, '2024-12-04 20:00:29', 'Y');
INSERT INTO carpool_map (cmId, carpool_id, user_id, order_time, is_cancel) VALUES(82, 14, 17, '2024-12-07 15:39:14', 'Y');
INSERT INTO carpool_map (cmId, carpool_id, user_id, order_time, is_cancel) VALUES(83, 13, 14, '2024-12-08 14:19:25', 'N');



INSERT INTO approve_todo_list (atl_id, user_id, role_id, msg, apply_time, is_approve) VALUES(10, 9, 200, '申請成為司機', '2024-11-27 15:59:20', 'Y');
INSERT INTO approve_todo_list (atl_id, user_id, role_id, msg, apply_time, is_approve) VALUES(11, 10, 200, '申請成為司機', '2024-11-27 15:59:28', 'Y');
INSERT INTO approve_todo_list (atl_id, user_id, role_id, msg, apply_time, is_approve) VALUES(12, 10, 200, '申請成為司機', '2024-11-29 16:59:49', 'TBD');
INSERT INTO approve_todo_list (atl_id, user_id, role_id, msg, apply_time, is_approve) VALUES(13, 12, 200, '申請成為司機', '2024-11-29 22:32:18', 'Y');
INSERT INTO approve_todo_list (atl_id, user_id, role_id, msg, apply_time, is_approve) VALUES(14, 9, 200, '申請成為司機', '2024-12-02 15:06:45', 'Y');
INSERT INTO approve_todo_list (atl_id, user_id, role_id, msg, apply_time, is_approve) VALUES(15, 15, 200, '申請成為司機', '2024-12-03 14:53:40', 'TBD');
INSERT INTO approve_todo_list (atl_id, user_id, role_id, msg, apply_time, is_approve) VALUES(16, 15, 200, '申請成為司機', '2024-12-03 14:53:58', 'Y');
INSERT INTO approve_todo_list (atl_id, user_id, role_id, msg, apply_time, is_approve) VALUES(21, 14, 200, '申請成為司機', '2024-12-07 15:37:36', 'TBD');
INSERT INTO approve_todo_list (atl_id, user_id, role_id, msg, apply_time, is_approve) VALUES(23, 17, 200, '申請成為司機', '2024-12-07 15:51:49', 'TBD');



