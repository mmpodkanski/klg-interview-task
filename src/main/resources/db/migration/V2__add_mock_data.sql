INSERT INTO objects_for_rent(
    object_name,
    price,
    object_space,
    description
) VALUES ('Obiekt1', 105, 20, 'description1'), ('Obiekt2', 120.6, 67, 'description2'), ('Obiekt3', 66.99, 15, '123');

INSERT INTO reservations(
    days_booked,
    start_date,
    end_date,
    lessor_id,
    tenant_id,
    object_id,
    cost
) VALUES (25, '2022-04-03', '2022-04-28', 4, 1, 1, 500), (10, '2022-05-10', '2022-05-20', 2, 1, 1, 650.23), (2, '2022-04-03', '2022-04-5', 3, 2, 2, 12.55);

INSERT INTO users(
   username
) VALUES ('MARIUSZ'), ('MARCIN'), ('MICHAL'), ('ADAM');

