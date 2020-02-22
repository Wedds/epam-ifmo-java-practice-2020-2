BEGIN;

DROP table invoice,
    car_damage,
    orders,
    users,
    passport,
    driving_license,
    car,
    car_model;

DROP type e_status_car,
    e_status_damage,
    e_role_users,
    e_status_invoice,
    e_status_order;

COMMIT;
