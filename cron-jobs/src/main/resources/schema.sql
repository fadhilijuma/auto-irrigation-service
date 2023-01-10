CREATE OR REPLACE VIEW slots_view AS
SELECT   row_number() over (order by (select 1)) as id,p.id as plot_id, sensor_id, (cast(c.ideal_moisture_content as int) - cast(p.current_moisture_content as int)) as volume
FROM  plots p
          INNER JOIN  crops  c
                      ON      c.id = p.crop_id
WHERE p.current_moisture_content < c.ideal_moisture_content;