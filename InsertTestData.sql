use superherosightingTest;

INSERT INTO `superheroSightingTest`.`heroes` (`id`, `name`, `description`) VALUES ('1', 'Superman', 'Man of Steel');
INSERT INTO `superheroSightingTest`.`heroes` (`id`, `name`, `description`) VALUES ('2', 'Batman', 'psychologically damaged orphan');
INSERT INTO `superheroSightingTest`.`heroes` (`id`, `name`, `description`) VALUES ('3', 'Captain Marvel', 'Top Gun, Aliens, 90\'s');
INSERT INTO `superheroSightingTest`.`heroes` (`id`, `name`, `description`) VALUES ('4', 'Iron Man', 'funny guy');
INSERT INTO `superheroSightingTest`.`heroes` (`id`, `name`, `description`) VALUES ('5', 'Captain America', 'not a funny guy');
INSERT INTO `superheroSightingTest`.`heroes` (`id`, `name`, `description`) VALUES ('6', 'Thor', 'also not a funny guy');
INSERT INTO `superheroSightingTest`.`heroes` (`id`, `name`, `description`) VALUES ('7', 'Spiderman', 'Tom Holland version');
INSERT INTO `superheroSightingTest`.`heroes` (`id`, `name`, `description`) VALUES ('8', 'Spiderman', 'Toby Macguire');


INSERT INTO `superherosightingtest`.`powers` (`id`, `name`, `description`) VALUES ('1', 'Super Strength', 'generic throw people, move vehicles, etc');
INSERT INTO `superherosightingtest`.`powers` (`id`, `name`, `description`) VALUES ('2', 'Advanced Tech (human)', 'rich people smh');
INSERT INTO `superherosightingtest`.`powers` (`id`, `name`, `description`) VALUES ('3', 'Physics Ignoring Flight', 'really not fair');
INSERT INTO `superherosightingtest`.`powers` (`id`, `name`, `description`) VALUES ('4', 'Funny Quips', 'really pulls the scene together');
INSERT INTO `superherosightingtest`.`powers` (`id`, `name`, `description`) VALUES ('5', 'Spider Shit', 'webs, crawling on walls');
INSERT INTO `superherosightingtest`.`powers` (`id`, `name`, `description`) VALUES ('6', 'Advanced Tech (cosmic)', 'is it magic? is it science?');

INSERT INTO `superherosightingtest`.`heroSuperpowers` (`heroId`, `powerId`) VALUES ('1', '1');
INSERT INTO `superherosightingtest`.`heroSuperpowers` (`heroId`, `powerId`) VALUES ('1', '3');
INSERT INTO `superherosightingtest`.`heroSuperpowers` (`heroId`, `powerId`) VALUES ('2', '2');
INSERT INTO `superherosightingtest`.`heroSuperpowers` (`heroId`, `powerId`) VALUES ('3', '1');
INSERT INTO `superherosightingtest`.`heroSuperpowers` (`heroId`, `powerId`) VALUES ('3', '3');
INSERT INTO `superherosightingtest`.`heroSuperpowers` (`heroId`, `powerId`) VALUES ('3', '4');
INSERT INTO `superherosightingtest`.`heroSuperpowers` (`heroId`, `powerId`) VALUES ('3', '6');
INSERT INTO `superherosightingtest`.`heroSuperpowers` (`heroId`, `powerId`) VALUES ('4', '2');
INSERT INTO `superherosightingtest`.`heroSuperpowers` (`heroId`, `powerId`) VALUES ('4', '3');
INSERT INTO `superherosightingtest`.`heroSuperpowers` (`heroId`, `powerId`) VALUES ('4', '4');
INSERT INTO `superherosightingtest`.`heroSuperpowers` (`heroId`, `powerId`) VALUES ('5', '1');
INSERT INTO `superherosightingtest`.`heroSuperpowers` (`heroId`, `powerId`) VALUES ('5', '2');
INSERT INTO `superherosightingtest`.`heroSuperpowers` (`heroId`, `powerId`) VALUES ('6', '1');
INSERT INTO `superherosightingtest`.`heroSuperpowers` (`heroId`, `powerId`) VALUES ('6', '3');
INSERT INTO `superherosightingtest`.`heroSuperpowers` (`heroId`, `powerId`) VALUES ('6', '4');
INSERT INTO `superherosightingtest`.`heroSuperpowers` (`heroId`, `powerId`) VALUES ('6', '6');
INSERT INTO `superherosightingtest`.`heroSuperpowers` (`heroId`, `powerId`) VALUES ('7', '2');
INSERT INTO `superherosightingtest`.`heroSuperpowers` (`heroId`, `powerId`) VALUES ('7', '4');
INSERT INTO `superherosightingtest`.`heroSuperpowers` (`heroId`, `powerId`) VALUES ('7', '5');
INSERT INTO `superherosightingtest`.`heroSuperpowers` (`heroId`, `powerId`) VALUES ('8', '5');
INSERT INTO `superherosightingtest`.`heroSuperpowers` (`heroId`, `powerId`) VALUES ('8', '1');

INSERT INTO `superherosightingtest`.`locations` (`id`, `name`, `description`, `address`, `latitude`, `longitude`) VALUES ('1', 'Unknown', 'who knows', 'n/a', '0.000000', '0.000000');
INSERT INTO `superherosightingtest`.`locations` (`id`, `name`, `description`, `address`, `latitude`, `longitude`) VALUES ('2', 'Times Square', 'lots of builidngs, lots of skybeams', 'Manhattan, NY 10036', '40.759036', '-73.985109');
INSERT INTO `superherosightingtest`.`locations` (`id`, `name`, `description`, `latitude`, `longitude`) VALUES ('3', 'Bikini Bottom', 'somewhere in the ocean', '11.611954', '165.363370');
INSERT INTO `superherosightingtest`.`locations` (`id`, `name`, `description`, `address`, `latitude`, `longitude`) VALUES ('4', 'T.G.I. Friday\'s', 'everyday is friday\'s', '1039 Interstate Pkwy, Akron OH', '40.978914', '-81.484867');
INSERT INTO `superherosightingtest`.`locations` (`id`, `name`, `description`) VALUES ('5', 'Undisclosed', 'we don\'t know where');
INSERT INTO `superherosightingtest`.`locations` (`id`, `name`, `description`, `address`, `latitude`, `longitude`) VALUES ('6', 'Hollywood Sign', 'This thing will probably get destroyed', 'Los Angeles, CA 90068', '34.134305', '-118.321537');

INSERT INTO `superherosightingtest`.`organizations` (`id`, `name`, `description`, `locationId`, `phone`, `email`) VALUES ('1', 'Justice League of Super Acquaintences', 'Spongebob', '2', '3334445555', 'email@bikinibottom.gov');
INSERT INTO `superherosightingtest`.`organizations` (`id`, `name`, `description`, `locationId`, `phone`, `email`) VALUES ('2', 'The Avengers', 'you know them', '1', '6667778888', 'tonystark@hotmail.com');
INSERT INTO `superherosightingtest`.`organizations` (`id`, `name`, `description`, `locationId`) VALUES ('3', 'Thor\'s Book Club', 'they\'re reading Hillbilly Elegy right now', '3');

INSERT INTO `superherosightingtest`.`organizationMembership` (`heroId`, `organizationId`) VALUES ('8', '1');
INSERT INTO `superherosightingtest`.`organizationMembership` (`heroId`, `organizationId`) VALUES ('3', '2');
INSERT INTO `superherosightingtest`.`organizationMembership` (`heroId`, `organizationId`) VALUES ('4', '2');
INSERT INTO `superherosightingtest`.`organizationMembership` (`heroId`, `organizationId`) VALUES ('5', '2');
INSERT INTO `superherosightingtest`.`organizationMembership` (`heroId`, `organizationId`) VALUES ('6', '2');
INSERT INTO `superherosightingtest`.`organizationMembership` (`heroId`, `organizationId`) VALUES ('7', '2');
INSERT INTO `superherosightingtest`.`organizationMembership` (`heroId`, `organizationId`) VALUES ('6', '3');
INSERT INTO `superherosightingtest`.`organizationMembership` (`heroId`, `organizationId`) VALUES ('8', '3');
INSERT INTO `superherosightingtest`.`organizationMembership` (`heroId`, `organizationId`) VALUES ('3', '3');
INSERT INTO `superherosightingtest`.`organizationMembership` (`heroId`, `organizationId`) VALUES ('2', '3');

INSERT INTO `superherosightingtest`.`sightings` (`id`, `sightingDate`, `description`, `locationId`) VALUES ('1', '2019-01-01', 'Couple Hero\'s blowing up the Hollywood sign', '5');
INSERT INTO `superherosightingtest`.`sightings` (`id`, `sightingDate`, `description`, `locationId`) VALUES ('2', '2019-03-02', 'Altercation in a TGI Firday\'s parking lot', '3');
INSERT INTO `superherosightingtest`.`sightings` (`id`, `sightingDate`, `description`, `locationId`) VALUES ('3', '2018-11-25', 'SkyBeam in NYC', '1');
INSERT INTO `superherosightingtest`.`sightings` (`id`, `sightingDate`, `description`, `locationId`) VALUES ('4', '2017-10-13', 'Plankton is on the loose again', '2');
INSERT INTO `superherosightingtest`.`sightings` (`id`, `sightingDate`, `description`, `locationId`) VALUES ('5', '2019-02-14', 'another Skybeam and alien attack', '1');

INSERT INTO `superherosightingtest`.`heroAtSighting` (`heroId`, `sightingId`) VALUES ('1', '1');
INSERT INTO `superherosightingtest`.`heroAtSighting` (`heroId`, `sightingId`) VALUES ('2', '1');
INSERT INTO `superherosightingtest`.`heroAtSighting` (`heroId`, `sightingId`) VALUES ('6', '1');
INSERT INTO `superherosightingtest`.`heroAtSighting` (`heroId`, `sightingId`) VALUES ('8', '2');
INSERT INTO `superherosightingtest`.`heroAtSighting` (`heroId`, `sightingId`) VALUES ('6', '2');
INSERT INTO `superherosightingtest`.`heroAtSighting` (`heroId`, `sightingId`) VALUES ('4', '2');
INSERT INTO `superherosightingtest`.`heroAtSighting` (`heroId`, `sightingId`) VALUES ('1', '3');
INSERT INTO `superherosightingtest`.`heroAtSighting` (`heroId`, `sightingId`) VALUES ('2', '3');
INSERT INTO `superherosightingtest`.`heroAtSighting` (`heroId`, `sightingId`) VALUES ('3', '3');
INSERT INTO `superherosightingtest`.`heroAtSighting` (`heroId`, `sightingId`) VALUES ('7', '3');
INSERT INTO `superherosightingtest`.`heroAtSighting` (`heroId`, `sightingId`) VALUES ('1', '4');
INSERT INTO `superherosightingtest`.`heroAtSighting` (`heroId`, `sightingId`) VALUES ('6', '5');
INSERT INTO `superherosightingtest`.`heroAtSighting` (`heroId`, `sightingId`) VALUES ('7', '5');
INSERT INTO `superherosightingtest`.`heroAtSighting` (`heroId`, `sightingId`) VALUES ('8', '5');

