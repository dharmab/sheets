ALTER TABLE characters
ADD COLUMN inspiration BOOLEAN NOT NULL DEFAULT FALSE,
ADD COLUMN current_hit_dice INTEGER NOT NULL DEFAULT 0,
ADD COLUMN maximum_hit_dice INTEGER NOT NULL DEFAULT 0;